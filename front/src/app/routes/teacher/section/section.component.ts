import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, ModalHelper} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from '@delon/abc';
import {filter} from "rxjs/operators";
import {TeacherSectionEditComponent} from "./edit/edit.component";
import {TeacherSectionViewComponent} from "./view/view.component";

@Component({
  selector   : 'app-teacher-section',
  templateUrl: './section.component.html',
})
export class TeacherSectionComponent implements OnInit {

  params: any = {};
  dataSet: any[] = [];

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
        {text: '查看', type: 'none', click: (record) => this.view(record)},
        {text: '删除', type: 'del', click: (record) => this.delete(record)}
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    const url = `sections/my`;
    this.http.get(url).subscribe((data: any) => {
      this.dataSet = data;
    })
  }

  view(record) {
    this.modal
    .static(TeacherSectionViewComponent, {record: record, i: record})
    .pipe(filter(w => w === true))
    .subscribe(() => this.reloadData());
  }

  delete(record) {
    const url = `sections/my/${record.id}`;
    this.http.delete(url).subscribe(() => {
      this.reloadData();
    })
  }

  add() {
    this.modal
    .static(TeacherSectionEditComponent, {i: {}})
    .pipe(filter(w => w === true))
    .subscribe(() => this.reloadData());
  }

}
