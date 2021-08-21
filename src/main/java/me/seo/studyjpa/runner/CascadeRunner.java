package me.seo.studyjpa.runner;

import me.seo.studyjpa.domain.Comment;
import me.seo.studyjpa.domain.Post;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/*
@Component
@Transactional
public class CascadeRunner implements ApplicationRunner {
    @PersistenceContext
    EntityManager manager;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 가정 : 게시글이 있을 때 게시글을 저장하거나 게시글을 삭제할때 comment도 같이 저장 되거나 삭제 되어야함

        Post post = new Post();
        post.setTitle("spring boot ");

        Comment comment = new Comment();
        comment.setComment("good!!");

        post.addComment(comment);

        Comment comment2 = new Comment();
        comment2.setComment("I love it ");

        post.addComment(comment2);

        Session session = manager.unwrap(Session.class);
        // 이 경우 post만 저장이 된다. 당연 comment를 save 하지 않았기 때문에
        // but save할 때 comment도 같이 저장되게 하고 싶다
        session.save(post);

        Post post1 = session.get(Post.class,post.getId());


         //remove 할 때 comment도 remove
         //Post post1 = session.get(Post.class, 1L);
         //session.remove(post1); //Cascade remove
         //Cascade All



    }
}

 */