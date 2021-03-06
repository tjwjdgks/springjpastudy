package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository // bean을 등록할 필요 없음
public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findByTitleContains(String title, Pageable pagable);
    long countByTitleContains(String title);
}
