package me.seo.studyjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

// 중간에 사용되는 repository는 NoRepositoryBean 추가 해야한다
@NoRepositoryBean
public interface MyRepository<T,Id extends Serializable> extends JpaRepository<T, Id> {

    // 어떤 entity가 persistence context에 들어가 있는 지 확인
    boolean cotains(T entity);

}
