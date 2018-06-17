import {Component, Inject, Injectable} from '@angular/core';
import { Router } from '@angular/router';
import { SettingsService } from '@delon/theme';
import {DA_SERVICE_TOKEN, ITokenService, LocalStorageStore} from '@delon/auth';
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'header-user',
  template: `
  <nz-dropdown nzPlacement="bottomRight">
    <div class="item d-flex align-items-center px-sm" nz-dropdown>
      <nz-avatar [nzSrc]="settings.user.avatar" nzSize="small" class="mr-sm"></nz-avatar>
      {{settings.user.name}}
    </div>
    <div nz-menu class="width-sm">
      <!--<div nz-menu-item [nzDisabled]="true"><i class="anticon anticon-user mr-sm"></i>个人中心</div>-->
      <!--<div nz-menu-item [nzDisabled]="true"><i class="anticon anticon-setting mr-sm"></i>设置</div>-->
      <li nz-menu-divider></li>
      <div nz-menu-item (click)="logout()"><i class="anticon anticon-setting mr-sm"></i>退出登录</div>
    </div>
  </nz-dropdown>
  `,
})
export class HeaderUserComponent {
  constructor(
    public settings: SettingsService,
    private router: Router,
  ) {}

  logout() {
    this.router.navigateByUrl('/passport/login').then();
  }
}
