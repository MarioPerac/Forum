import { Component } from '@angular/core';
import { CommentService } from '../../services/comment/comment.service';
import { Comment } from '../../models/comment.model';

@Component({
  selector: 'app-control',
  templateUrl: './control.component.html',
  styleUrl: './control.component.css'
})
export class ControlComponent {
  comments: Comment[] = [];
  editingCommentId: number | null = null;
  editedContent: string = '';

  constructor(private commentService: CommentService) { }

  ngOnInit(): void {
    this.loadComments();
  }

  loadComments(): void {
    this.commentService.getNew().subscribe(
      {
        next: (comments: Comment[]) => {
          if (comments != null)
            this.comments = comments;
        }
      }
    )
  }

  startEditing(comment: Comment): void {
    this.editingCommentId = comment.id;
    this.editedContent = comment.content;
  }

  saveEdit(comment: Comment): void {
    comment.content = this.editedContent;

  }

  cancelEdit(): void {
    this.editingCommentId = null;
  }

  onPublish(comment: Comment): void {
    comment.published = true;
    this.commentService.update(comment).subscribe({ next: () => { } });
    this.comments = this.comments.filter(c => c.id != comment.id);
  }

  onForbidden(comment: Comment): void {
    comment.forbidden = true;
    this.commentService.update(comment).subscribe({ next: () => { } });
    this.comments = this.comments.filter(c => c.id != comment.id);
  }
}
