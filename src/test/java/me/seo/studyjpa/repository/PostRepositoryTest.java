package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Post;
import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    /*
    test 기본적으로 Transactional 그리고 Transactional일때 roll back하는 것 spring 기본 제공
    jpa 기본적으로 불필요한 query 발생하지 않음 따라서 save에서 insert가 작동되지 않을 수 있음
     */
    @Test
    @Rollback(false) // query 보고 싶을 경우
    public void crudRepository(){
        // Given
        Post post = new Post();
        post.setTitle("test post");
        assertNull(post.getId());
        // When
        Post save = postRepository.save(post);
        // Then
        assertNotNull(save);
        // When
        List<Post> posts = postRepository.findAll();
        // Then
        assertEquals(posts.size(),1);
        assertTrue(posts.contains(save));

        // When 보통 Pagable 파라미터 사용 PagingAndSortingRepository 기능
        // pagenumber 0 , 10개
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        assertEquals(page.getTotalElements(),1);
        assertEquals(page.getNumber(),0);
        assertEquals(page.getTotalElements(),1);
        assertEquals(page.getSize(),10);
        System.out.println(postRepository.countByTitleContains("test"));

    }
}