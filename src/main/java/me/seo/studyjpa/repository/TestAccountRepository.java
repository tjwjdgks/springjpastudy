package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.TestAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TestAccountRepository extends JpaRepository<TestAccount,Long>, QuerydslPredicateExecutor<TestAccount> {
}
