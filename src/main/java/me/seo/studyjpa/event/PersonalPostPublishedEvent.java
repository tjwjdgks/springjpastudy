package me.seo.studyjpa.event;


import lombok.Getter;
import me.seo.studyjpa.domain.PersonalPost;
import org.springframework.context.ApplicationEvent;


@Getter
public class PersonalPostPublishedEvent extends ApplicationEvent{
    private final PersonalPost personalPost;

    public PersonalPostPublishedEvent(Object source) {
        super(source);
        this.personalPost = (PersonalPost) source;
    }
}
