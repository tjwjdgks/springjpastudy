package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Book {
    @Id @GeneratedValue
    private Long id;

    String title;

    @Lob
    String content;

    LocalDateTime published;

}
