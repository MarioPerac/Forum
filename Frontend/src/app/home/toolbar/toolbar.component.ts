import { Component } from '@angular/core';
import { LoginService } from '../../services/login/login.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrl: './toolbar.component.css'
})
export class ToolbarComponent {

  constructor(public loginService: LoginService, private router: Router, private cookieService: CookieService) { }

  onLogoutClick() {
    localStorage.removeItem("token");
    this.router.navigate(['login']);
  }

  onHomeClick() {
    this.router.navigate(['home']);
  }

}
