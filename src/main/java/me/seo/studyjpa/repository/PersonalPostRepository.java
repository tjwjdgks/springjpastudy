package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.PersonalPost;
import me.seo.studyjpa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
// jpa 기능도 사용하면서 사용자 custom 리포지토리도 사용한다
/*
public interface PersonalPostRepository extends JpaRepository<PersonalPost,Long>, PersonalCustomPostRepository {

}

 */
// 커스텀 repo 상속 받는 것
public interface PersonalPostRepository extends MyRepository<PersonalPost,Long> {

}
