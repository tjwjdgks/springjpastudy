package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.PersonalPost;
import me.seo.studyjpa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// jpa 기능도 사용하면서 사용자 custom 리포지토리도 사용한다
/*
public interface PersonalPostRepository extends JpaRepository<PersonalPost,Long>, PersonalCustomPostRepository {

}

 */
// 커스텀 repo 상속 받는 것
// 커스터 마이징 했기 때문에 QuerydslPredicateExecutor 구현해야 되지만 커스터마이징 구현체는 구현하지 않아 에러 발생 but 버전 올라가면서 문제 해결

public interface PersonalPostRepository extends MyRepository<PersonalPost,Long>, QuerydslPredicateExecutor<PersonalPost> {

}
