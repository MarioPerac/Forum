import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../../models/comment.model';
import { CommentRequest } from '../../models/requests/comment-request.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private commentsUrl = 'http://localhost:8080/api/comments';

  private areaUrl = 'http://localhost:8080/api/area';
  constructor(private http: HttpClient) { }

  get(name: string) {
    const url = `${this.areaUrl}/${name}/comments`;
    return this.http.get<Comment[]>(url);
  }

  create(comment: CommentRequest) {
    return this.http.post<Comment>(this.commentsUrl, comment);
  }

  getNew() {
    return this.http.get<Comment[]>(this.commentsUrl + "/new");
  }

  update(comment: Comment) {
    return this.http.put(this.commentsUrl, comment);
  }
}
