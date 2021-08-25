package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
// query dsl 사용 방법
public interface BookRepository extends JpaRepository<Book,Long>, QuerydslPredicateExecutor<Book> {

}
