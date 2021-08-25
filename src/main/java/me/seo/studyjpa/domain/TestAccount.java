package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TestAccount {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private String firstname;

    private String lastname;
}
