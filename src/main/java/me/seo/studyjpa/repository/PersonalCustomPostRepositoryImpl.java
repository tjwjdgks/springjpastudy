package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.PersonalPost;
import me.seo.studyjpa.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
// 네이밍 컨벤션 상속받은 리포지토리에 Impl로 붙이고 구현한다
public class PersonalCustomPostRepositoryImpl implements PersonalCustomPostRepository {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<PersonalPost> findMyPost() {
        System.out.println("custom findMyPost");
        return entityManager.createQuery("SELECT p FROM PersonalPost as p", PersonalPost.class)
                .getResultList();
    }
}
