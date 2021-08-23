package me.seo.studyjpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// repository 공통으로 정의할 수 있다
@NoRepositoryBean
// id serializeable로 받아야 한다
public interface MyInterface<T,Id extends Serializable> extends Repository<T,Id> {
    <E extends T> E save (E entity);
    // 값이 없을 경우 비어있는 List return, null은 나오지 않는다
    List<T> findAll();

    long count();
    // optional 추천
    <E extends T> Optional<E> findById(Id id);
}
