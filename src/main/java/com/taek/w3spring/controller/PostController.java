package com.taek.w3spring.controller;

import com.taek.w3spring.domain.Post;
import com.taek.w3spring.domain.PostRepository;
import com.taek.w3spring.domain.PostRequestDto;
import com.taek.w3spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/views/postInfo")
    public List<Post> readPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/views/writePost")
    public ModelAndView viewCreatePost(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("createPost");
        return mv;
    }

    @PostMapping("/create/writePost")
    public void createPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        postRepository.save(post);

        String username1 = post.getUsername();
        String title1 = post.getTitle();
        String content1 = post.getContent();
        LocalDateTime cr = post.getCreatedAt();
        LocalDateTime md = post.getModifiedAt();

        System.out.println("~~~ : "+username1 + ",  "+title1+ ", "+content1);
        System.out.println("~~~cr : "+cr+ ", ~~~" +md);
    }

    @GetMapping("/views/posting/{id}")
    public ModelAndView viewPost(@PathVariable Long id){
        ModelAndView mv = new ModelAndView();

        // Post형에 Optional 형을 넣는데 되는데.. 근데 예외처리 안하면 형이 달라서 안된다.
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재x")
        );

        mv.addObject("post",post);
        mv.addObject("timeAt",post.getModifiedAt());
        mv.setViewName("viewPost");
        return mv;
    }

    @DeleteMapping("/api/delete/{id}")
    public Long deleteMemo(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/update/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        // 파라미터 안에는 받아올 데이터가 있다.
        return postService.update(id, requestDto);
    }
}
