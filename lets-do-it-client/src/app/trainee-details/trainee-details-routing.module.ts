import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { TraineeDetailsComponent } from './trainee-details.component';

const routes: Routes = [
  {
    path: '',
    component: TraineeDetailsComponent
  },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TraineeDetailsRoutingModule { }
