import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { StudentRoutingModule } from './student-routing.module';
import { StudentStudyPlanComponent } from './study-plan/study-plan.component';
import { StudentStudyPlanEditComponent } from './study-plan/edit/edit.component';
import { StudentStudyPlanViewComponent } from './study-plan/view/view.component';

const COMPONENTS = [
  StudentStudyPlanComponent];
const COMPONENTS_NOROUNT = [
  StudentStudyPlanEditComponent,
  StudentStudyPlanViewComponent];

@NgModule({
  imports: [
    SharedModule,
    StudentRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class StudentModule { }
