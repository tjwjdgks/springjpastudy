package me.seo.studyjpa.controller;

import me.seo.studyjpa.domain.Post;
import me.seo.studyjpa.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PostRepository postRepository;
    @Test
    public void getPost() throws Exception {
        Post post = new Post();
        post.setTitle("test");
        postRepository.save(post);

        mockMvc.perform(get("/post/"+post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is("test")));
    }
    @Test
    // 모든 test는 public void
    public void getPosts() throws Exception {
        Post post = new Post();
        post.setTitle("test");
        postRepository.save(post);

        mockMvc.perform(get("/posts/")
                    .param("page","0")
                    .param("size","10")
                    .param("sort","title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title",is("test")));
    }
    @Test
    public void getPostsH() throws Exception {
        createPost();

        mockMvc.perform(get("/posts/hateoas")
                .param("page","3")
                .param("size","10")
                .param("sort","title"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    private void createPost() {
        int postCount = 100;
        while(postCount>0){
            Post post = new Post();
            post.setTitle("test");
            postRepository.save(post);
            postCount--;
        }
    }
}