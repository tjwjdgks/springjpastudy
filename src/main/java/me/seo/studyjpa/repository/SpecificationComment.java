package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecificationComment extends JpaRepository<Comment,Long>, JpaSpecificationExecutor<Comment> {
}
