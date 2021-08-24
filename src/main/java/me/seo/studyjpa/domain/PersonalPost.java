package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;
import me.seo.studyjpa.event.PersonalPostPublishedEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PersonalPost extends AbstractAggregateRoot<PersonalPost> {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @Lob // 255자가 넘을 수도 있을 때
    private String content;

    LocalDateTime created;

    public PersonalPost publish() {
        this.registerEvent(new PersonalPostPublishedEvent(this));
        return this;
    }
}
