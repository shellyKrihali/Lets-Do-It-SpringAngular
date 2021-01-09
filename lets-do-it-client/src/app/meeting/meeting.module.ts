import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MeetingsComponent } from './meetings.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MapModule } from '../homepage/map/map.module';
import { MeetingInfoComponent } from './meeting-info/meeting-info.component';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { FindMeetingComponent } from './find-meeting/find-meeting.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatInputModule } from '@angular/material/input';
import { MeetingRoutingModule } from './meeting-routing.module';


@NgModule({
  declarations: [MeetingsComponent, MeetingInfoComponent, FindMeetingComponent],
  imports: [
    CommonModule,
    MatTabsModule,
    MatCardModule,
    MatExpansionModule,
    MatGridListModule,
    MatInputModule,
    MeetingRoutingModule,
    MapModule
  ],
  exports: [MeetingsComponent],
})
export class MeetingModule { }
