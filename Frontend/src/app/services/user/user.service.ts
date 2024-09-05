import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Program } from '../../models/program.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getUserPrograms(username: string): Observable<Program[]> {
    const url = this.apiUrl + "/" + username + "/programs";
    return this.http.get<Program[]>(url);
  }

  getUserActviePrograms(username: string) {
    const url = this.apiUrl + "/" + username + "/programs/active";
    return this.http.get<Program[]>(url);
  }

  getUserFinishedPrograms(username: string) {
    const url = this.apiUrl + "/" + username + "/programs/finished";
    return this.http.get<Program[]>(url);
  }
}
