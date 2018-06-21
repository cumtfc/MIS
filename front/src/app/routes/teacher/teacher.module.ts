import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { TeacherRoutingModule } from './teacher-routing.module';
import { TeacherCourseComponent } from './course/course.component';
import { TeacherCourseEditComponent } from './course/edit/edit.component';
import { TeacherArrangeComponent } from './arrange/arrange.component';
import { TeacherArrangeEditComponent } from './arrange/edit/edit.component';
import { TeacherArrangeViewComponent } from './arrange/view/view.component';

const COMPONENTS = [
  TeacherCourseComponent,
  TeacherArrangeComponent];
const COMPONENTS_NOROUNT = [
  TeacherCourseEditComponent,
  TeacherArrangeEditComponent,
  TeacherArrangeViewComponent];

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
