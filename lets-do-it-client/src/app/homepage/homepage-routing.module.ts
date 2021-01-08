import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage.component';
import { MapModule } from './map/map.module';
import { AgmCoreModule } from '@agm/core';
import { MatCardModule } from '@angular/material/card';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomepageComponent
  },
];

@NgModule({
  imports: [
    MatCardModule,
    MapModule,
    RouterModule.forChild(routes),
    AgmCoreModule.forRoot({
      apiKey: ''
    }),],
  exports: [RouterModule],
})
export class HomepageRoutingModule { }