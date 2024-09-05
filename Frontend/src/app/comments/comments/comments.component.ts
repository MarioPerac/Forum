import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from '../../services/comment/comment.service';
import { CommentRequest } from '../../models/requests/comment-request.model';
import { Comment } from '../../models/comment.model';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  comments!: Comment[];
  areaName!: string; 
  newComment: CommentRequest = new CommentRequest('', '', 1);

  constructor(private route: ActivatedRoute, private commentService: CommentService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.areaName = params.get('name') || '';
      this.loadComments();
    });
  }

  loadComments(): void {
    this.commentService.get(this.areaName).subscribe({
      next: (comments: Comment[]) => {

        if (comments == null) {
          this.comments = [];
        }
        else {
          this.comments = comments;
        }

      }
    });
  }

  submitComment(): void {
    if (!this.newComment.content.trim()) {
      return;
    }

    

  }
}
