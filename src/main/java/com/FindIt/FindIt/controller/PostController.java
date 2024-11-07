package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.entity.PostEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> src/main/java/com/FindIt/FindIt/controller/PostController.java
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {


    /* 게시판 생성 페이지 이동 */
    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/update"/* /{postId}/ */)
    public String postUpdate(/* @PathVariable int postId, */ Model model) {
        //PostEntity post = postService.findPost(postId);
        //model.addAllAttributes("post", post);
        return "update";
    }

}
