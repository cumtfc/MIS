import { Component, OnInit, ViewChild } from '@angular/core';
  import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
  import { _HttpClient } from '@delon/theme';
  import { SFSchema, SFUISchema } from '@delon/form';
import {SimpleTableColumn, SimpleTableComponent} from "@delon/abc";

  @Component({
    selector: 'app-teacher-section-edit',
    templateUrl: './edit.component.html',
  })
  export class TeacherSectionEditComponent implements OnInit {
    record: any = {};
    dataSet: any[] = [];
    params: any = {};
    @ViewChild('st') st: SimpleTableComponent;
    columns: SimpleTableColumn[] = [
      {title: '排课号', index: 'sectionSn'},
      {title: '课程编号', index: 'courseSn'},
      {title: '课程名', index: 'courseName'},
      {title: '学分', index: 'credit'},
      {title: '教室', index: 'room'},
      {title: '周次', index: 'dayOfWeek'},
      {title: '时间', index: 'timeOfDay'},
      {title: '容量', index: 'capacity'},
      {
        title  : '',
        buttons: [
          {text: '选择', type: 'none', click: (record) => this.choose(record)},
        ]
      }
    ];

    constructor(
      private modal: NzModalRef,
      public msgSrv: NzMessageService,
      public http: _HttpClient,
    ) {}

    ngOnInit() {
      this.reloadData();
    }

    reloadData() {
      const url = `sections/teacher/available`;
      this.http.get(url).subscribe((data: any) => {
        this.dataSet = data;
      });
    }

    private choose(record) {
      const url = `sections/choose`;
      this.http.post(url,record).subscribe((data: any) => {
        this.msgSrv.success('选课成功');
        this.reloadData();
      });
      // console.log(record)
    }

    close() {
      this.modal.destroy();
    }
  }
