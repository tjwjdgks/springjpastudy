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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        List<Comment> jpa = commentRepository.findByCommentContainsIgnoreCase("jpa");
        assertEquals(jpa.size(),1);

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