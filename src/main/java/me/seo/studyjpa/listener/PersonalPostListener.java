package me.seo.studyjpa.listener;


import me.seo.studyjpa.domain.PersonalPost;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PersonalPostListener  {

    @EventListener
    public void test(PersonalPost personalPost) {
        System.out.println("=======================");
        System.out.println(personalPost + " is published!");
        System.out.println("=======================");

    }
}
