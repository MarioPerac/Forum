import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountsRoutingModule } from './accounts-routing.module';
import { AccountsComponent } from './accounts/accounts.component';
import { HomeModule } from '../home/home.module';
import { AppMaterialModule } from '../app-material/app-material.module';


@NgModule({
  declarations: [
    AccountsComponent
  ],
  imports: [
    CommonModule,
    AccountsRoutingModule,
    HomeModule,
    AppMaterialModule
  ]
})
export class AccountsModule { }
