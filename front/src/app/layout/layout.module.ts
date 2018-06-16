import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { LayoutDefaultComponent } from './default/default.component';
import { HeaderComponent } from './default/header/header.component';
import { SidebarComponent } from './default/sidebar/sidebar.component';
import { HeaderFullScreenComponent } from './default/header/components/fullscreen.component';
import { HeaderStorageComponent } from './default/header/components/storage.component';
import { HeaderUserComponent } from './default/header/components/user.component';
import {LayoutPassportComponent} from "./passport/passport.component";

const COMPONENTS = [
  LayoutDefaultComponent,
  LayoutPassportComponent,
  HeaderComponent,
  SidebarComponent
];

const HEADERCOMPONENTS = [
  HeaderFullScreenComponent,
  HeaderStorageComponent,
  HeaderUserComponent
];

@NgModule({
  imports: [SharedModule],
  providers: [],
  declarations: [
    ...COMPONENTS,
    ...HEADERCOMPONENTS,
  ],
  exports: [
    ...COMPONENTS,
  ]
})
export class LayoutModule { }
