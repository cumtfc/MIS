import {NgModule} from '@angular/core';
import {SharedModule} from '@shared/shared.module';
import {StudentRoutingModule} from './student-routing.module';
import {StudentStudyPlanComponent} from './study-plan/study-plan.component';
import {StudentStudyPlanEditComponent} from './study-plan/edit/edit.component';
import {StudentSelectionComponent} from './selection/selection.component';
import {StudentSelectionEditComponent} from './selection/edit/edit.component';
import {StudentTranscriptComponent} from './transcript/transcript.component';

const COMPONENTS = [
  StudentStudyPlanComponent,
  StudentSelectionComponent,
  StudentTranscriptComponent];
const COMPONENTS_NOROUNT = [
  StudentStudyPlanEditComponent,
  StudentSelectionEditComponent,
];

@NgModule({
  imports        : [
    SharedModule,
    StudentRoutingModule
  ],
  declarations   : [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class StudentModule {
}
