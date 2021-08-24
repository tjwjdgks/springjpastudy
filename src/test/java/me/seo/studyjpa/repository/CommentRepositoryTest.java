package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Comment;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

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