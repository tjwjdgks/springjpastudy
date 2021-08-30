package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Comment;
import me.seo.studyjpa.domain.Post;
import org.assertj.core.error.ShouldBeAfterYear;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // 슬라이싱 테스트
@DataJpaTest // 슬라이싱 테스트
//@SpringBootTest // 전체 테스트
//@ActiveProfiles("test")  // 전체 테스트
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    // commetRepo specification
    @Autowired
    SpecificationComment specificationComment;

    @Test
    @DisplayName("comment custom test")
    public void curd(){
        Comment comment = getComment("Hello test");

        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findAll();

        assertEquals(comments.size(), 1);
        Optional<Comment> byId = commentRepository.findById(100L);
        assertTrue(byId.isEmpty());
    }
    @Test
    public void containsKeyword(){
        Comment comment = getComment("spring jpa");
        comment.setLikeCount(2);
        commentRepository.save(comment);

        List<Comment> spring = commentRepository.findByCommentContains("spring");
        assertFalse(spring.isEmpty());

        List<Comment> size = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring",1);
        assertEquals(size.size(),1);
    }
    @Test
    public void orderBy(){
        Comment comment1 = getComment("spring jpa");
        comment1.setLikeCount(100);

        Comment comment2 = getComment("test spring");
        comment2.setLikeCount(10);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> spring = commentRepository.findByCommentContainsOrderByIdDesc("spring");
        for(Comment s : spring)
            System.out.println(s.getId());

    }
    @Test
    public void pageTest(){
        Comment comment = getComment("test1");
        Comment comment2 = getComment("test2");

        commentRepository.save(comment);
        commentRepository.save(comment2);

        Page<Comment> byIdLessThan = commentRepository.findByIdLessThan(5L, PageRequest.of(0,3));
        assertEquals(byIdLessThan.getTotalElements(),2);
    }
    @Test
    public void pageTest2(){
        this.createComment("spring",100);
        this.createComment("spring2 ",300);
        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"LikeCount"));
        Page<Comment> spring = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);
        assertEquals(spring.getTotalElements(),2);
        assertEquals(spring.getContent().get(0).getLikeCount(),300);
    }
    @Test
    public void asyncTest() throws ExecutionException, InterruptedException {
        this.createComment("spring data",100);
        this.createComment("test data" ,50);

        ListenableFuture<List<Comment>> data = commentRepository.findByCommentContainsIgnoreCase("data");
        System.out.println("==========");
        System.out.println("is done? " +data.isDone()); // data 불러오기 다 되었는 지 확인
        //  그냥 Future의 경우 get을 호출한 경우 가져 올때 까지 기다린다
        //List<Comment> comments = data.get();
        //comments.forEach(System.out::println);
        data.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onSuccess(List<Comment> comments) {
                System.out.println("==============");
                comments.forEach(System.out::println);
            }
        });
    }

    // many to one 인 경우에는 fetch 모드가 eager, 끝이 one으로 끝나는 경우 eager, 끝이 many로 끝나는 경우 lazy
    @Test
    public void getComment(){
        Post post = new Post();
        post.setTitle("test post");

        Comment comment = new Comment();
        comment.setComment("comment");

        post.addComment(comment);
        Post save = postRepository.save(post);

        List<Comment> all = commentRepository.findAll();
        System.out.println(all.get(0).getComment());

    }
    @Test
    public void getCommentTest(){
        commentRepository.findById(1L);
        System.out.println("==============");
        commentRepository.getById(1L);
    }

    @Test
    public void getProjectionTest(){
        Post post = new Post();
        post.setTitle("jpa");

        Comment comment = new Comment();
        comment.setComment("test");
        comment.setDown(100);
        comment.setUp(200);

        post.addComment(comment);
        Post save = postRepository.save(post);
        List<ProjectionComment> byId = commentRepository.findByPost_id(save.getId(),ProjectionComment.class);
        assertFalse(byId.isEmpty());
        System.out.println(byId.get(0).getVotes());

    }
    // specification test
    // pageable도 가능하다
    // 여러가지 조합을 할경우 test 철저하게 하기
    @Test
    public void spec(){
        //specificationComment.findAll(SpecificationCommentSpec.isBest());

        //specificationComment.findAll(SpecificationCommentSpec.isBest().or(SpecificationCommentSpec.isGood()));
        // pageable
        Page<Comment> all = specificationComment.findAll(SpecificationCommentSpec.isBest().or(SpecificationCommentSpec.isGood()), PageRequest.of(0, 10));

    }
    // query by example test
    // 단점 조건의 유연하게 만들지 못함 , 제한적이다 , 이런 기능이 있다 정도로...
    @Test
    public void qbe(){
        // 이 자체가 prove = 필드에 어떤 값을 가지고 있는 도메인 객체
        Comment prove = new Comment();
        prove.setBest(true);

        // best만 확인 나머지 무시
        //ExampleMatcher는 Prove에 들어있는 그 필드의 값들을 어떻게 쿼리할 데이터와 비교할지 정의한 것.
        // 문자열은 starts/contains/ends/regex 가 가능하고 그밖에 property는 값이 정확히 일치해야 한다.
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny();
        //Example은 그 둘을 하나로 합친 것. 이걸로 쿼리를 함.
        Example<Comment>example = Example.of(prove,exampleMatcher);

        commentRepository.findAll(example);

    }
    /*
    @Test
    public void auditingtest(){
        Comment comment = new Comment();
        comment.setComment("test");
        Comment save = commentRepository.save(comment);
        List<Comment> all = commentRepository.findAll();

    }

     */
    private Comment getComment(String test1) {
        Comment comment = new Comment();
        comment.setComment(test1);
        return comment;
    }
    private void createComment(String test1,int like) {
        Comment comment = new Comment();
        comment.setComment(test1);
        comment.setLikeCount(like);
        commentRepository.save(comment);
    }


}