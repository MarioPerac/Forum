import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ControlRoutingModule } from './control-routing.module';
import { ControlComponent } from './control/control.component';
import { HomeModule } from '../home/home.module';
import { AppMaterialModule } from '../app-material/app-material.module';


@NgModule({
  declarations: [
    ControlComponent
  ],
  imports: [
    CommonModule,
    ControlRoutingModule,
    HomeModule,
    AppMaterialModule
  ]
})
export class ControlModule { }
