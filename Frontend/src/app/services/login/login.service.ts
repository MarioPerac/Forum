import { Injectable } from '@angular/core';
import { User } from '../../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Login } from '../../models/login.model';
import { catchError, map, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/api/users/login';


  public user: User | null = null;
  public signedIn: boolean = false;

  constructor(private http: HttpClient) { }

  public login(login: Login) {
    return this.http.post<any>(this.apiUrl, login);
  }
}
