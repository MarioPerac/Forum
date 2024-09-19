import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { User } from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getRole(): Observable<string> {
    return this.http.get<{ role: string }>(this.apiUrl + "/role").pipe(map(response => response.role));
  }

  setActivated(username: string, activated: boolean, roleId: number) {
    return this.http.put(this.apiUrl + "/" + username + "/activation", { activated, roleId });
  }

  getUnactivated() {
    return this.http.get<User[]>(this.apiUrl + "/unactivated");
  }
}
