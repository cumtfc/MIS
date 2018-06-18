import {Component, OnInit, ViewChild} from '@angular/core';
import {filter} from 'rxjs/operators';
import {_HttpClient, ModalHelper, TitleService} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from '@delon/abc';
import {SFSchema} from '@delon/form';
import {TeacherCourseEditComponent} from "./edit/edit.component";

@Component({
  selector   : 'app-teacher-course',
  templateUrl: './course.component.html',
})
export class TeacherCourseComponent implements OnInit {

  params: any = {};
  dataSet: any = [];
  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    {title: '编号', index: 'courseSn'},
    {title: '课程名', index: 'courseName'},
    {title: '学分', index: 'credit'},
    {title: '先修课程', index: 'prevCoursesString'},
    {
      title  : '',
      buttons: [
        {text: '编辑', type: 'none', click: (record) => this.edit(record)},
        {text: '删除', type: 'del', click: (record) => this.delete(record)}
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper,private titleService:TitleService) {
  }

  ngOnInit() {
    this.titleService.setTitle('课程管理');
    this.reloadData();

  }

  reloadData() {
    const url = `courses`;
    this.http.get(url).subscribe((data: any) => {
      this.dataSet = data;
    });
  }

  edit(record) {
    this.modal
    .static(TeacherCourseEditComponent, {record: record, courses: this.dataSet})
    .pipe(filter(w => w === true))
    .subscribe(() => this.reloadData());
  }

  delete(record) {
    const url = `courses/${record.id}`;
    this.http.delete(url).subscribe(()=>{
      this.reloadData();
    })
  }

  add() {
    this.modal
    .static(TeacherCourseEditComponent, {courses: this.dataSet})
    .pipe(filter(w => w === true))
    .subscribe(() => this.reloadData());
  }

}
