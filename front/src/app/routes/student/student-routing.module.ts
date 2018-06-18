import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentStudyPlanComponent } from './study-plan/study-plan.component';

const routes: Routes = [

  { path: 'study-plan', component: StudentStudyPlanComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
