package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Comment;
import me.seo.studyjpa.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
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

    @Async
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String keyword);

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int n);
    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

    List<Comment> findByCommentContainsOrderByIdDesc(String keyword);

    Page<Comment> findByIdLessThan(Long id, Pageable pageable);
    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    // fetch mode lazy 지만 eager로 받고 싶을 때
    // 기본 값 patch 설정한 애트리 뷰트는 eager 나머지 lazy // 기본 type들은 eager로 가져옴 // load도 설정 값은 eager모드로 가져온다
    //@EntityGraph(value = "Comment.post") // 방법 1
    // getById는  실제 테이블을 조회하는 대신 프록시 객체만 가져온다.
    // 프록시 객체만 있는 경우 ID 값을 제외한 나머지 값을 사용하기 전까지는 실제 DB 에 액세스 하지 않기 때문에 SELECT 쿼리가 날아가지 않는다.
    @EntityGraph(attributePaths="post")
    Optional<Comment> getById(Long id);
}
