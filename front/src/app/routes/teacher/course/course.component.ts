import { Component, OnInit, ViewChild } from '@angular/core';
import { filter } from 'rxjs/operators';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { SimpleTableColumn, SimpleTableComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
import {TeacherCourseEditComponent} from "./edit/edit.component";

@Component({
  selector: 'app-teacher-course',
  templateUrl: './course.component.html',
})
export class TeacherCourseComponent implements OnInit {

    params: any = {};
    dataSet:any = [];
    searchSchema: SFSchema = {
      properties: {
        id: {
          type: 'string',
          title: '编号'
        }
      }
    };
    @ViewChild('st') st: SimpleTableComponent;
    columns: SimpleTableColumn[] = [
      { title: '编号', index: 'courseSn' },
      { title: '课程名', index: 'courseName' },
      { title: '学分', index: 'credit' },
      { title: '先修课程', index: 'prevCourses' },
      {
        title: '',
        buttons: [
          { text: '查看', click: (item: any) => `/form/${item.id}` },
          { text: '编辑', type: 'static', component: TeacherCourseEditComponent, click: 'reload' },
        ]
      }
    ];

    constructor(private http: _HttpClient, private modal: ModalHelper) { }

    ngOnInit() {

      this.reloadData();

    }

    reloadData(){
      const url = `courses`;
      this.http.get(url).subscribe((data: any) => {
        this.dataSet = data;
      });
    }

    add() {
      this.modal
        .static(TeacherCourseEditComponent, { courses:this.dataSet })
        .pipe(filter(w => w === true))
        .subscribe(() => this.reloadData());
    }

}
