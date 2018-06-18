import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TeacherCourseComponent} from './course/course.component';

const routes: Routes = [
  {path: '', redirectTo: 'course'},
  {path: 'course', component: TeacherCourseComponent,data:{title:'课程管理'}}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule {
}
