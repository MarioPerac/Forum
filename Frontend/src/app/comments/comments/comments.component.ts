import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommentService } from '../../services/comment/comment.service';
import { CommentRequest } from '../../models/requests/comment-request.model';
import { Comment } from '../../models/comment.model';
import { Area } from '../../models/area.model';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  comments!: Comment[];
  areaName!: string;
  newComment!: CommentRequest;

  constructor(private route: ActivatedRoute, private commentService: CommentService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.areaName = params.get('name') || '';
      console.log(this.areaName);
      this.newComment = new CommentRequest('', this.getValueFromArea(this.areaName));
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

    this.commentService.create(this.newComment).subscribe({
      next: (comment: Comment) => {
        this.snackBar.open("Comment added.", undefined, {
          duration: 2000
        });
      }
    })
  }

  getValueFromArea(name: string): number {
    if ("Culture" === name) {
      return Area.Culture;
    } else if ("Music" === name) {
      return Area.Music;
    } else if ("Sport" === name) {
      return Area.Sport;
    } else if ("Science" === name) {
      return Area.Science;
    }
    return 0;
  }
}
