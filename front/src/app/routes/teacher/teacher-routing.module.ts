import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TeacherCourseComponent} from './course/course.component';
import {TeacherArrangeComponent} from './arrange/arrange.component';

const routes: Routes = [
  {path: '', redirectTo: 'course'},
  {path: 'courses', component: TeacherCourseComponent, data: {title: '课程管理'}},
  {path: 'arrange', component: TeacherArrangeComponent, data: {title: '教务排课'}}
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule {
}
