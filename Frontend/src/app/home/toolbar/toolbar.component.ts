import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login/login.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrl: './toolbar.component.css'
})
export class ToolbarComponent implements OnInit {

  role: string = "ROLE_USER";

  constructor(public loginService: LoginService, private router: Router, private cookieService: CookieService, private userService: UserService) { }
  ngOnInit(): void {
    this.userService.getRole().subscribe({
      next: (role: string) => {
        this.role = role;
      }
    })
  }

  onLogoutClick() {
    localStorage.removeItem("token");
    this.router.navigate(['login']);
  }

  onHomeClick() {
    this.router.navigate(['home']);
  }

  onAccountsClick() {
    this.router.navigate(['accounts']);
  }

  onControlClick() {
    this.router.navigate(['control']);
  }
}
