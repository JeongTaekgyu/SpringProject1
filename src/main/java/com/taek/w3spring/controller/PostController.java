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

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

//    @GetMapping("/views/postInfo'")
//    public List<Post> readPost(){
//        return postRepository.findAllByOrderByModifiedAtDesc();
//    }

    // RestController에서는 안된다
    /*@GetMapping("/test1")
    public String helloTest(Model model){
        System.out.println("~~~ 오긴와?");
        model.addAttribute("data","제발 되라~~");
        System.out.println("~~~ 오긴와?222");
        return "hello";
    }*/

    @GetMapping("/test1")
    public ModelAndView helloTest(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("data","모델 앤 뷰입니다");
        mv.setViewName("hello");
        return mv;
    }

    @GetMapping("/views/writePost")
    public ModelAndView viewCreatePost(){
        ModelAndView mv = new ModelAndView();

        String test = "테스트요.";
        String test2 = "두 번째 입니다.";
        mv.addObject("one",test);
        mv.addObject("two",test2);
        mv.setViewName("createPost");
        return mv;
    }

    @PostMapping("/create/writePost")
    public void createPost(@RequestBody PostRequestDto requestDto){
        //ModelAndView mv = new ModelAndView();

        Post post = new Post(requestDto);
        postRepository.save(post);

        String username1 = post.getUsername();
        String title1 = post.getTitle();
        String content1 = post.getContent();
        LocalDateTime cr = post.getCreatedAt();
        LocalDateTime md = post.getModifiedAt();

        System.out.println("~~~ : "+username1 + ",  "+title1+ ", "+content1);
        System.out.println("~~~cr : "+cr+ ", ~~~" +md);
    //        mv.addObject("name",username1);
    //        mv.addObject("title",title1);
    //        mv.addObject("content",content1);
    //        mv.setViewName("hello");
        //return "redirect:/test1";
        //return mv;

    }

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
