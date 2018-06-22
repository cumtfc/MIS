import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { StudentRoutingModule } from './student-routing.module';
import { StudentStudyPlanComponent } from './study-plan/study-plan.component';
import { StudentStudyPlanEditComponent } from './study-plan/edit/edit.component';
import { StudentSelectionComponent } from './selection/selection.component';
import { StudentSelectionEditComponent } from './selection/edit/edit.component';
import { StudentTranscriptComponent } from './transcript/transcript.component';
import { StudentTranscriptEditComponent } from './transcript/edit/edit.component';
import { StudentTranscriptViewComponent } from './transcript/view/view.component';

const COMPONENTS = [
  StudentStudyPlanComponent,
  StudentSelectionComponent,
  StudentTranscriptComponent];
const COMPONENTS_NOROUNT = [
  StudentStudyPlanEditComponent,
  StudentSelectionEditComponent,
  StudentTranscriptEditComponent,
  StudentTranscriptViewComponent];

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
