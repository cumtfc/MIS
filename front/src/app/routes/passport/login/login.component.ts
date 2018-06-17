import {SettingsService} from '@delon/theme';
import {Component, Inject, OnDestroy, OnInit, Optional} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import {DA_SERVICE_TOKEN, SocialService, TokenService,} from '@delon/auth';
import {ReuseTabService} from '@delon/abc';
import {StartupService} from '@core/startup/startup.service';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'passport-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less'],
  providers: [SocialService],
})
export class UserLoginComponent implements OnInit,OnDestroy {
  form: FormGroup;
  error = '';
  type = 0;
  loading = false;

  constructor(
    fb: FormBuilder,
    private router: Router,
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private settingsService: SettingsService,
    private http:HttpClient,
    @Optional()
    @Inject(ReuseTabService)
    private reuseTabService: ReuseTabService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService,
    private startupSrv: StartupService,
  ) {
    this.form = fb.group({
      username: [null, [Validators.required]],
      password: [null, Validators.required],
      remember: [true],
    });
    modalSrv.closeAll();
  }

  // region: fields

  get username() {
    return this.form.controls.username;
  }
  get password() {
    return this.form.controls.password;
  }

  // endregion

  submit() {
    this.error = '';
      this.username.markAsDirty();
      this.username.updateValueAndValidity();
      this.password.markAsDirty();
      this.password.updateValueAndValidity();
      if (this.username.invalid || this.password.invalid) return;
    // mock http
    this.loading = true;
    const url = `login`;
    let formData: FormData = new FormData();
    formData.set('username',this.username.value);
    formData.set('password',this.password.value);
    // formData.append('username',this.username.value);
    let data = `username=${this.username.value}&password=${this.password.value}&submit=Login`;
    this.http.post(url, data,{headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
    .subscribe(res=>{
      this.loading = false;
      //todo 返回后的相关操作
      this.msg.success("登陆成功");
      // 清空路由复用信息
      this.reuseTabService.clear();
      // 重新获取 StartupService 内容，若其包括 User 有关的信息的话
      this.startupSrv.load().then(() => this.router.navigate(['/']));
      // 否则直接跳转
      // this.router.navigate(['/']);
    });
  }

  ngOnDestroy(): void {

  }

  ngOnInit(): void{
    const url = `logout`;
    this.http.get(url).subscribe();
  }
}
