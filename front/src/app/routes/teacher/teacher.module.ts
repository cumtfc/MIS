import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { TeacherRoutingModule } from './teacher-routing.module';
import { TeacherCourseComponent } from './course/course.component';
import { TeacherCourseEditComponent } from './course/edit/edit.component';

const COMPONENTS = [
  TeacherCourseComponent];
const COMPONENTS_NOROUNT = [
  TeacherCourseEditComponent,];

@NgModule({
  imports: [
    SharedModule,
    TeacherRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class TeacherModule { }
