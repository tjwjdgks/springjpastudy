package me.seo.studyjpa.runner;

import me.seo.studyjpa.domain.Account;
import me.seo.studyjpa.domain.Study;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@Transactional // 반드시 EntityManager와 관련된 모든 operation은 한 transaction 안에서 일어나야 한다
// class의 경우 모든 메소드에 transaction 설정
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext // jpa annotation
    EntityManager entityManager; // jpa 가장 핵심 클래스
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setName("seo");
        account.setPassword("test");
        account.setLocalDateTime(LocalDateTime.now(ZoneOffset.UTC));

        Study study = new Study();
        study.setName("spring jpa");

        // 양방향 일때 주의 해야 할점 관계의 주인인 쪽에다 관계를 mapping 해야한다
        // 그렇지 않을 경우 관계가 생기지 않는다, 양방향이 ManyToOne 이 주인이므로 Study에 맵핑

        // 양방향 이므로 둘다 하는 것 추천
        // 따라서 함수로 만드는 것도 좋다 account.addStudy 함수 참고
        /*
        study.setOwner(account); // 반드시 해야한다
        account.getStudies().add(study); // optional이다
         */
        account.addStudy(study);

        Session session = entityManager.unwrap(Session.class); // hibernate의 가장 큰 핵심적인 api session
        session.save(account);// 영속화 할 수 있다 즉 data 저장 할 수 있다
        session.save(study);
        // 이 경우 select query 발생 하지 않는다. cache된 값 return
        Account load = session.load(Account.class, account.getId());


    }
}
