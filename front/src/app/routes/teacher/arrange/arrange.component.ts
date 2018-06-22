import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, ModalHelper} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from '@delon/abc';
import {SFSchema} from '@delon/form';
import {TeacherArrangeEditComponent} from "./edit/edit.component";
import {filter} from "rxjs/operators";
import {TeacherCourseEditComponent} from "../course/edit/edit.component";

@Component({
  selector   : 'app-teacher-arrange',
  templateUrl: './arrange.component.html',
})
export class TeacherArrangeComponent implements OnInit {

  params: any = {};
  dataSet: any = [];

  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
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
        {text: '编辑', type: 'none', click: (record) => this.edit(record)},
        {text: '删除', type: 'del', click: (record) => this.delete(record)}
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit() {
    this.reloadData();
  }

  private reloadData() {
    const url = `sections`;
    this.http.get(url).subscribe(data => {
      this.dataSet = data;
    });
  }

  edit(record) {
    this.modal
    .static(TeacherArrangeEditComponent, {record: record,i:record})
    .pipe(filter(w => w === true))
    .subscribe(() => this.reloadData());
  }

  delete(record) {
    const url = `sections/${record.id}`;
    this.http.delete(url).subscribe(()=>{
      this.reloadData();
    })
  }


  add() {
    this.modal
      .static(TeacherArrangeEditComponent)
      .pipe(filter(w => w === true))
      .subscribe(() => this.reloadData());
  }

}
