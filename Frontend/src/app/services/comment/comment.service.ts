import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../../models/comment.model';
import { CommentRequest } from '../../models/requests/comment-request.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private apiUrl = 'http://localhost:8080/api/comments';

  constructor(private http: HttpClient) { }

  get(areaName: string) {
    return this.http.get<Comment[]>(this.apiUrl);
  }
}
