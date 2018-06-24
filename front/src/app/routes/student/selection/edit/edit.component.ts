import { Component, OnInit, ViewChild } from '@angular/core';
  import { NzModalRef, NzMessageService } from 'ng-zorro-antd';
  import { _HttpClient } from '@delon/theme';
  import { SFSchema, SFUISchema } from '@delon/form';
import {SimpleTableColumn, SimpleTableComponent} from "@delon/abc";

  @Component({
    selector: 'app-student-selection-edit',
    templateUrl: './edit.component.html',
  })
  export class StudentSelectionEditComponent implements OnInit {

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
      {title: '容量', index: 'capacityWithFraction'},
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
      const url = `sections/student/available`;
      this.http.get(url).subscribe((data: any) => {
        this.dataSet = data;
      });
    }

    private choose(record) {
      const url = `transcripts`;
      this.http.post(url,record).subscribe((data: any) => {
        console.log(data);
        this.msgSrv.success(data.msg);
        this.reloadData();
      });
    }

    close() {
      this.modal.destroy();
    }
  }
