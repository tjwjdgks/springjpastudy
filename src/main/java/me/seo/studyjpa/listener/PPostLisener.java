package me.seo.studyjpa.listener;


import me.seo.studyjpa.event.PersonalPostPublishedEvent;
import org.springframework.context.ApplicationListener;

public class PPostLisener implements ApplicationListener<PersonalPostPublishedEvent> {
    @Override
    public void onApplicationEvent(PersonalPostPublishedEvent personalPostPublishedEvent) {
        System.out.println("=================");
        System.out.println("=== event !!!! ==== ");
        System.out.println("=================");
    }
}
