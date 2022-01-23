package com.taek.w3spring.controller;

import com.taek.w3spring.domain.Post;
import com.taek.w3spring.domain.PostRepository;
import com.taek.w3spring.domain.PostRequestDto;
import com.taek.w3spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    /*@GetMapping("/api/test")
    public List<Post> readPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }*/

    @GetMapping("/hellotest")
    public String helloTest(Model model){
        model.addAttribute("data","제발 되라~~");
        return "hello";
    }

    @GetMapping("/views/writePost")
    public ModelAndView viewCreatePost(){
        ModelAndView mv = new ModelAndView();
        String test = "테스트입니다.";
        String test2 = "두 번째 입니다.";
        mv.addObject("one",test);
        mv.addObject("two",test2);
        mv.setViewName("createPost");
        return mv;
    }

    /*@PostMapping("/create/writePost")
    public ModelAndView createPost(@RequestBody PostRequestDto requestDto){
        ModelAndView mv = new ModelAndView();

    }*/

    @GetMapping("/views/Post")
    public ModelAndView viewPost(){
        ModelAndView mv = new ModelAndView();
        String name = "정택규";
        mv.addObject("name",name);
        mv.setViewName("viewPost");
        return mv;
    }


    /*@PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        // 파라미터 안에는 받아올 데이터가 있다.
        return postService.update(id, requestDto);
    }*/
}
