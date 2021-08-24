package me.seo.studyjpa.repository;


import me.seo.studyjpa.domain.PersonalPost;
import me.seo.studyjpa.domain.Post;

import java.util.List;

// 커스텀 리포지토리 만들기
// spring data 리포지토리 재정의 가능
// entity manager persistence, detach, removed 까지 관리 entity의 상태
// jpa repo 에 있는 것 재정의 한다면 구현이 중복된다 spring data jpa는 내가 커스텀하게 구현한 구현체가 우선순위가 높다
public interface PersonalCustomPostRepository {

    List<PersonalPost> findMyPost();
}

