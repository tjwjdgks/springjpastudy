package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.PersonalPost;
import me.seo.studyjpa.event.PersonalPostPublishedEvent;
import me.seo.studyjpa.listener.PersonalPostListener;
import me.seo.studyjpa.testConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(testConfig.class)
class PersonalPostTest {
    @Autowired
    PersonalPostRepository personalPostRepository;

    // 이벤트 보내는 방식
    // 이벤트를 만들고 이벤트 리스너를 받아 처리 하는 코드
    /*
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void event0(){
        PersonalPost personalPost = new PersonalPost();
        personalPost.setTitle("event");
        applicationEventPublisher.publishEvent(personalPost);
    }
     */
    // save 할 때 event로
    @Autowired
    ApplicationContext context;
    @Test
    public void event(){
        PersonalPost personalPost = new PersonalPost();
        personalPost.setTitle("hibernate");

        personalPostRepository.save(personalPost.publish());
        System.out.println("===========");
        List<PersonalPost> all = personalPostRepository.findAll();

    }
    @Test
    public void event2(){
        PersonalPost personalPost = new PersonalPost();
        personalPost.setTitle("test");
        PersonalPostPublishedEvent s = new PersonalPostPublishedEvent(personalPost);
        context.publishEvent(s);

    }
    @Test
    public void curd(){
        PersonalPost personalPost = new PersonalPost();
        personalPost.setTitle("hibernate");

        assertFalse(personalPostRepository.cotains(personalPost));

        personalPostRepository.save(personalPost);

        assertTrue(personalPostRepository.cotains(personalPost));

    }
}