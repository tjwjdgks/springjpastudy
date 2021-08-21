package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

// 이런 식으로 만들 수 있음
/*
@Repository
@Transactional
public class JustJpa {
    @PersistenceContext
    EntityManager entityManager;

    public Post add(Post post){
        entityManager.persist(post);
        return post;
    }
    public void delete(Post post){
        entityManager.remove(post);

    }
    public List<Post> findAll(){
        TypedQuery<Post> query = entityManager.createQuery("SELECT p from Post as p", Post.class);
        List<Post> resultList = query.getResultList();
        return resultList;
    }
}

 */