package org.unibl.etf.sni.forum.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.sni.forum.base.CrudController;
import org.unibl.etf.sni.forum.models.dto.Comment;
import org.unibl.etf.sni.forum.models.requests.CommentRequest;
import org.unibl.etf.sni.forum.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController extends CrudController<Integer, CommentRequest, Comment> {
    CommentService commentService;
    protected CommentController(CommentService crudService) {
        super(crudService, Comment.class);
        this.commentService = crudService;
    }
}
