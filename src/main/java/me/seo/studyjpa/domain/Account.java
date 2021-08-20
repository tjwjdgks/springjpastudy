package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity// (name="") // 이름 변경 가능
@Getter
@Setter
public class Account {
    // ddl update 경우에 이미 column이 만들어진 후에 설정을 변경해도 변경사항이 table column에 적용되지 않는다
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    // 사실상 @Column 애노테이션 생략 된 것
    private String name;

    private String password;

    private LocalDateTime localDateTime;
    /*
    단방향 일때
    OneToMany, 이 경우 jointable로 만들어진다 또한 관계의 주인이 Account
    Account 에서 study를 관리 한다고 가정 했을 때
    @OneToMany
    private Set<Study> studies = new HashSet<>();
     */
    // composite한 value;
    // 양방향 일때
    @OneToMany(mappedBy = "owner") // 이러한 종속 정보를 알려주지 않을 경우 단방향 두개가 생긴다
    private Set<Study> studies = new HashSet<>();
    @Embedded
    private Address address;
    @Transient // table mapping 하지 않음
    private Boolean check;

    // 관계를 표현하는 method convenient method
    // add
    public void addStudy(Study study){
        study.setOwner(this);
        this.getStudies().add(study);
    }
    // remove
    public void removeStudy(Study study){
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
