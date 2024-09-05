import { Injectable } from '@angular/core';
import { LoginService } from '../login/login.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class GuardService {

  constructor(private loginService: LoginService, private router: Router, private cookieService: CookieService) { }

  canActivate(): boolean {

    if (localStorage.getItem("token") != null) {
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }
}
