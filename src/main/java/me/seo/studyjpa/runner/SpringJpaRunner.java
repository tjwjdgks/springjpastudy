package me.seo.studyjpa.runner;

import me.seo.studyjpa.domain.Post;
import me.seo.studyjpa.repository.PostRepository;
import me.seo.studyjpa.testregister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SpringJpaRunner implements ApplicationRunner {
    @Autowired
    PostRepository postRepository;
    // bean을 등록 할 수 도 있음
    @Autowired
    testregister testregister;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(testregister.getName());
        Post post = new Post();
        post.setTitle("test");
        postRepository.save(post);
        postRepository.findAll().forEach(i->{
            System.out.println("============");
            System.out.println(i.getId());
            System.out.println("============");
        });

    }
}
