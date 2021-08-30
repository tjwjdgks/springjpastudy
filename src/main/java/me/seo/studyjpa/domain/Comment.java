package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


// 기본은 eager로 사용하지만 필요한 경우에 따라서 lazy로 가져오고 싶을 때
//@NamedEntityGraph(name ="Comment.post", attributeNodes = @NamedAttributeNode("post")) // 방법 1
// 연관 관계들만 정의 연관관계 사용은 커스텀하게 정의 하여 사용( JpaRepository 에서 정의)
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // auditing 기능을 위한 리스너 등록
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String comment;

    private boolean best;
    private int up;
    private int down;
    private Integer likeCount;

    // enum mapping
    @Enumerated(value= EnumType.STRING) // 기본은 type ordinal 0,1,2로 index mapping but type은 String 훨씬 안전, value로 들어간다
    private CommentStatus status;

    @CreatedDate
    private LocalDateTime created;

    @ManyToOne
    @CreatedBy
    private Account createdBy;

    @LastModifiedDate
    private LocalDateTime updated;

    @LastModifiedBy
    @ManyToOne
    private Account updatedBy;
    @ManyToOne(fetch = FetchType.LAZY) // 기본은 eager
    private Post post;

    // 엔티티가 저장되기 전에 호출된다
    @PrePersist
    public void prePersist(){
        System.out.println("pre persist is call ");
    }
}
