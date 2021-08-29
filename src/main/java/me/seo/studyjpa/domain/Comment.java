package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


// 기본은 eager로 사용하지만 필요한 경우에 따라서 lazy로 가져오고 싶을 때
//@NamedEntityGraph(name ="Comment.post", attributeNodes = @NamedAttributeNode("post")) // 방법 1
// 연관 관계들만 정의 연관관계 사용은 커스텀하게 정의 하여 사용( JpaRepository 에서 정의)
@Entity
@Getter
@Setter
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String comment;

    private boolean best;
    private int up;
    private int down;
    private Integer likeCount;
    @ManyToOne(fetch = FetchType.LAZY) // 기본은 eager
    private Post post;
}
