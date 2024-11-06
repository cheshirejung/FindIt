package com.FindIt.FindIt.controller;

import com.FindIt.FindIt.entity.PostEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

    @GetMapping("/update"/* /{postId}/ */)
    public String postUpdate(/* @PathVariable int postId, */ Model model) {
        //PostEntity post = postService.findPost(postId);
        //model.addAllAttributes("post", post);
        return "update";
    }

}
