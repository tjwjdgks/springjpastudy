package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Comment;
import me.seo.studyjpa.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

// 인터페이스 정의
// 아무 인터페이스 상속 받지 않아도 가능
// 아무런 기능이 들어 오지 않으므로 직접 구현
// spring jpa가 구현한다
//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//
public interface CommentRepository extends MyInterface<Comment,Long> {
    // @Query(value="SELECT c FROM Comment AS c")
    // 우선 순위 1. query, 2. procedure, .3 named query
    // 쿼리 만들기

    List<Comment> findByCommentContains(String keyword);
    List<Comment> findByCommentContainsIgnoreCase(String keyword);
    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int n);
    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

    List<Comment> findByCommentContainsOrderByIdDesc(String keyword);

    Page<Comment> findByIdLessThan(Long id, Pageable pageable);
    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

}
