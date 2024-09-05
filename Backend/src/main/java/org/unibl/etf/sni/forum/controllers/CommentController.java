package org.unibl.etf.sni.forum.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.sni.forum.base.CrudController;
import org.unibl.etf.sni.forum.models.dto.Comment;
import org.unibl.etf.sni.forum.models.requests.CommentRequest;
import org.unibl.etf.sni.forum.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController  {
    CommentService commentService;
    protected CommentController(CommentService crudService) {
        this.commentService = crudService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody CommentRequest commentRequest){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        commentRequest.setUsername(username);

        return commentService.insert(commentRequest, Comment.class);
    }
}
