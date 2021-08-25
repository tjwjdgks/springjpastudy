package me.seo.studyjpa.repository;

import com.querydsl.core.types.Predicate;
import me.seo.studyjpa.domain.Account;
import me.seo.studyjpa.domain.QAccount;
import me.seo.studyjpa.domain.QTestAccount;
import me.seo.studyjpa.domain.TestAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TestAccountRepositoryTest {
    @Autowired
    TestAccountRepository testAccountRepository;

    @Test
    public void curd(){
        // 변수 빼기 ctrl alt v
        QTestAccount testAccount = QTestAccount.testAccount;
        Predicate predicate = testAccount.firstname
                .containsIgnoreCase("seo")
                .and(testAccount.lastname.startsWith("jung"));
        Optional<TestAccount> one = testAccountRepository.findOne(predicate);
        assertTrue(one.isEmpty());
    }
}