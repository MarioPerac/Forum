import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User } from '../../models/user.model';
import { getRoleId } from '../../models/roles.model';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {
  users: User[] = [];
  roles: string[] = ['ADMIN', 'USER', 'MODERATOR'];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUnactivated().subscribe({
      next: (users: User[]) => {
        if (users == null)
          this.users = [];
        else {
          this.users = users;
        }
      }
    })
  }


  onActivate(user: User): void {
    this.userService.setActivated(user.username, true, getRoleId(user.role!)).subscribe({
      next: () => { }
    });
    this.users = this.users.filter(u => u.username != user.username);
  }

  onDeny(user: User): void {
    this.userService.setActivated(user.username, false, getRoleId(user.role!)).subscribe({
      next: () => { }
    });
    this.users = this.users.filter(u => u.username != user.username);
  }


}