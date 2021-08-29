package me.seo.studyjpa.repository;


import org.springframework.beans.factory.annotation.Value;

public interface ProjectionComment {
    String getComment();

    int getUp();

    int getDown();
    // 장점을 살리면서 closed 프로젝션
    // default 메서드
    // 커스텀 구현체 만든다
    default String getVotes(){
        return getUp() + " " + getDown();
    }
    /*
    // target은 comment, 이 경우 target을 한정짓지 못해서 다 comment를 다 가져와야함
    @Value("#{target.up+ ' ' + target.down}")
    String getVotes();

     */
}
