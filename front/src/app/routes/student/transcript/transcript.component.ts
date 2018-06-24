import { Component, OnInit, ViewChild } from '@angular/core';
import { filter } from 'rxjs/operators';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { SimpleTableColumn, SimpleTableComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';

@Component({
  selector: 'app-student-transcript',
  templateUrl: './transcript.component.html',
})
export class StudentTranscriptComponent implements OnInit {
  params: any = {};
  dataSet: any[] = [];

  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    {title: '排课号', index: 'sectionSn'},
    {title: '课程名', index: 'courseName'},
    {title: '教师', index: 'teacherName'},
    {title: '学分', index: 'credit'},
    {title: '教室', index: 'room'},
    {title: '周次', index: 'dayOfWeek'},
    {title: '时间', index: 'timeOfDay'},
    {title: '状态', index: 'state'},//state有选课成功和等待队列两种状态
    {title: '成绩', index: 'grade'}
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    const url = `transcripts/my`;
    this.http.get(url).subscribe((data: any) => {
      this.dataSet = data;
    });
  }

}
