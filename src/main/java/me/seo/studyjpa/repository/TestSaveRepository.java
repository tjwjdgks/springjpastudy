package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.TestSave;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestSaveRepository extends JpaRepository<TestSave,Long> {
    List<TestSave> findByTitleStartingWith(String title);
    // pTitle 는 alias
    /*
    @Query("SELECT p, p.title AS pTitle FROM TestSave AS p WHERE p.title = ?1") // native query 일 경우 native query = true 사용
    // 결과 sort 하고 싶을 때 sort 추가 // 정렬 옵션은 프로퍼티 또는 alias
    List<TestSave> findByTitle(String title, Sort sort); // findByTitle을 name을 찾을 때 사용
     */
    // jpa Named Parameter 과 SpEL
    // named 파라미터 사용가능하다 Param으로 받을 수 있음
    // SpEL도 가능하다 자세한 것은 https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#expressions
    //  ex) @Query에서 엔티티 이름을 #{#entityName}로 가능 SELECT p, p.title AS pTitle FROM #{#entityName} AS p WHERE p.title = :title
    @Query("SELECT p, p.title AS pTitle FROM TestSave AS p WHERE p.title = :title")
    List<TestSave> findByTitle(@Param("title")String title, Sort sort);

    // update query but 비 추천
    @Modifying(clearAutomatically = true) // update query 실행 했을 때 peristence context clear 해주는 것 해주어야 이전의 캐시 값을 지우고 새로운 값을 받을 수 있다
    @Query("UPDATE TestSave p Set p.title = ?1 WHERE p.id = ?2")
    int updateTitle(String title, Long id);
}
