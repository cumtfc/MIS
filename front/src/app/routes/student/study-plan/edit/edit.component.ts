import {Component, OnInit, ViewChild} from '@angular/core';
import {NzMessageService, NzModalRef} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from "@delon/abc";

@Component({
  selector   : 'app-student-study-plan-edit',
  templateUrl: './edit.component.html',
})
export class StudentStudyPlanEditComponent implements OnInit {
  record: any = {};
  plan: any[];

  params: any = {};
  dataSet: any;
  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    {title: '编号', index: 'courseSn'},
    {title: '课程名', index: 'courseName'},
    {title: '学分', index: 'credit'},
    {title: '先修课程', index: 'prevCoursesString'},
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
  ) {
  }

  ngOnInit(): void {
    const url = `courses`;
    this.http.get(url).subscribe((data: any[]) => {
      if (this.plan) {
        this.plan.forEach(c=>{
          let start = data.findIndex(t => t.id == c.id);
          if (start > -1) {
            data.splice(start, 1);
          }
        })
      }
      this.dataSet = data;
    });
  }

  close() {
    this.modal.destroy();
  }

  private choose(record) {
    const url = `students/studyPlans`;
    this.http.post(url,record).subscribe((data: any) => {
      this.msgSrv.success('选课成功');
    });
    // console.log(record)
  }
}
