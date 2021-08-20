package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Study {
    @Id @GeneratedValue // 기본 auto
    private Long id;

    private String name;

    /*
    단방향 일 때
    @ManyToOne // 현재 referenct에서 콜랙션이 아니고 1개 이므로 끝은 One
    // owner는 study를 여러개 만들 수 있다. ManyToOne 기본적으로 _id 와 왜래키 추가 된다
    private Account owner;

     */

    // 양방향 일때 @ManyToOne이 주인
    @ManyToOne
    private Account owner;
}
