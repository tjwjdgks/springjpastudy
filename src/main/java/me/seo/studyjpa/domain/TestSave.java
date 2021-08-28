package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Getter
@Setter
// jpa는 named쿼리 지원, 쿼리를 정의할 때 사용한 이름을 look up 해서 사용
// named쿼리 문제점 도메인 엔티티 클래스가 가독성이 떨어진다 @Query 에노테이션을 선호
// @NamedQuery(name = "TestSave.findByTitle", query = "SELECT p from TestSave AS p WHERE p.title = ?1")// entity 위에 정의  // jpql title이 첫번째 쿼리 와 같으면 찾기
public class TestSave {
    @Id @GeneratedValue
    Long id;

    String title;
}
