import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GuardService } from './services/guard/guard.service';

const routes: Routes = [
  {
    path: '', redirectTo: '/login', pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then(mod => mod.LoginModule)
  },
  {
    path: "home",
    loadChildren: () => import('./home/home.module').then(mod => mod.HomeModule),
    canActivate: [GuardService]
  },
  {
    path: "signup",
    loadChildren: () => import('./signup/signup.module').then(mod => mod.SignupModule)
  },
  {
    path: 'area/:name',
    loadChildren: () => import('./comments/comments.module').then(mod => mod.CommentsModule)
  },
  {
    path: 'accounts',
    loadChildren: () => import('./accounts/accounts.module').then(mod => mod.AccountsModule),
    canActivate: [GuardService]
  }
  ,
  {
    path: 'control',
    loadChildren: () => import('./control/control.module').then(mod => mod.ControlModule),
    canActivate: [GuardService]
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
