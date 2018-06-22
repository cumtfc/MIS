import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentStudyPlanComponent } from './study-plan/study-plan.component';
import { StudentSelectionComponent } from './selection/selection.component';
import { StudentTranscriptComponent } from './transcript/transcript.component';

const routes: Routes = [

  { path: 'study-plan', component: StudentStudyPlanComponent },
  { path: 'course-selection', component: StudentSelectionComponent },
  { path: 'transcript', component: StudentTranscriptComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
