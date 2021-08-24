package me.seo.studyjpa.repository;


import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
// 생성자 추가

public class MyRepositoryImpl<T, Id extends Serializable> extends SimpleJpaRepository<T,Id> implements MyRepository<T,Id> {

    private EntityManager entityManager;

    public MyRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public boolean cotains(T entity) {
        return entityManager.contains(entity);
    }
}
