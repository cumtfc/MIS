import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, ModalHelper} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from '@delon/abc';
import {SFSchema} from '@delon/form';

@Component({
  selector   : 'app-teacher-arrange',
  templateUrl: './arrange.component.html',
})
export class TeacherArrangeComponent implements OnInit {

  params: any = {};
  dataSet: any = [];
  searchSchema: SFSchema = {
    properties: {
      no: {
        type : 'string',
        title: '编号'
      }
    }
  };
  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    {title: '课程编号', index: 'course.courseSn'},
    {title: '课程名', index: 'course.courseName'},
    {title: '学分', index: 'course.credit'},
    {title: '教室', index: 'room'},
    {title: '周次', index: 'dayOfWeek'},
    {title: '时间', index: 'timeOfDay'},
    {title: '容量', index: 'capacity'},
    {
      title  : '',
      buttons: [
        // { text: '查看', click: (item: any) => `/form/${item.id}` },
        // { text: '编辑', type: 'static', component: FormEditComponent, click: 'reload' },
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit() {
    const url = `sections`;
    this.http.get(url).subscribe(data => {
      this.dataSet = data;
    });

  }

  add() {
    // this.modal
    //   .static(WareEditComponent, { i: { id: 0 } })
    //   .pipe(filter(w => w === true))
    //   .subscribe(() => this.st.reload());
  }

}
