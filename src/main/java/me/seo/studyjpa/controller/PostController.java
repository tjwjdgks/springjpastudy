package me.seo.studyjpa.controller;

import me.seo.studyjpa.domain.Post;
import me.seo.studyjpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {
    /*
    생성자가 하나만 있고 그 생성자가 받는 매개변수가 빈으로 등록되어 있으면 자동으로 빈을 등록해준다
    ex
    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

     */


    @Autowired
    PostRepository postRepository;

    // 도메인 클래스 컨버터 제공해준다
    /*
    Domain Class Converter
    ToIdConverter :  Entity를 Entity의 id로
    ToEntityConverter : Entity의 id를 받아 Entity로
     */
    // 도메인 클래스 컨버터 적용전
    /*
    @GetMapping("/post/{id}") // 핸들러
    public Post getPost(@PathVariable("id") Long id){ // 만약 path variable과 매개변수의 이름이 같으면 "id" 생략가능
        Optional<Post> byId = postRepository.findById(id);
        Post post = byId.get();
        return post;
    }
     */
    // 적용 후
    // converter 파라미터 -> id type -> entity
    @GetMapping("/post/{id}") // 핸들러
    public Post getPost(@PathVariable("id") Post post){ // 만약 path variable과 매개변수의 이름이 같으면 "id" 생략가능
        return post;
    }
    // page 기능도 가능 하다
    @GetMapping("/posts")
    public Page<Post> getPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }
    // Hateoas 기능 + webmvc 들어가 있어야 한다
    @GetMapping("/posts/hateoas")
    // PagedResourcesAssembler<entity type>
    // PagedResourcesAssembler는 entity type의 model을 만들어 준다
    public PagedModel<EntityModel<Post>> getPostsHateoas(Pageable pageable, PagedResourcesAssembler<Post> assembler){
        return assembler.toModel(postRepository.findAll(pageable));
    }
}
