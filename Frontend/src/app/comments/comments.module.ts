import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CommentsRoutingModule } from './comments-routing.module';
import { CommentsComponent } from './comments/comments.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { HomeComponent } from '../home/home/home.component';
import { HomeModule } from '../home/home.module';


@NgModule({
  declarations: [
    CommentsComponent
  ],
  imports: [
    CommonModule,
    CommentsRoutingModule,
    AppMaterialModule,
    HomeModule
  ]
})
export class CommentsModule { }
