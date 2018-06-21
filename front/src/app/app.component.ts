import { Component, HostBinding, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {_HttpClient, SettingsService, TitleService} from '@delon/theme';
import { filter } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`,
})
export class AppComponent implements OnInit {
  @HostBinding('class.layout-fixed')
  get isFixed() {
    return this.settings.layout.fixed;
  }
  @HostBinding('class.layout-boxed')
  get isBoxed() {
    return this.settings.layout.boxed;
  }
  @HostBinding('class.aside-collapsed')
  get isCollapsed() {
    return this.settings.layout.collapsed;
  }

  constructor(
    private settings: SettingsService,
    private router: Router,
    private titleSrv: TitleService,
    private http:HttpClient
  ) {}

  ngOnInit() {
    this.router.events
      .pipe(filter(evt => evt instanceof NavigationEnd))
      .subscribe(() => this.titleSrv.setTitle());
  }
}
