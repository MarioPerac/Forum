package org.unibl.etf.sni.forum.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.sni.forum.models.dto.Comment;
import org.unibl.etf.sni.forum.models.requests.CommentRequest;
import org.unibl.etf.sni.forum.services.CommentService;

import java.util.List;

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

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/new")
    public List<Comment> getNewComments(){
        return commentService.getNewComments();
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody CommentRequest request){
        commentService.update(request);
    }
}
