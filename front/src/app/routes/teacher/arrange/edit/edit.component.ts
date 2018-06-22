import {Component, OnInit} from '@angular/core';
import {NzMessageService, NzModalRef} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {SFSchema, SFUISchema} from '@delon/form';
import {SFSchemaEnumType} from "@delon/form/src/src/schema";
import {map, shareReplay} from "rxjs/operators";
import {Observable} from "rxjs/Observable";

@Component({
  selector   : 'app-teacher-arrange-edit',
  templateUrl: './edit.component.html',
})
export class TeacherArrangeEditComponent implements OnInit {
  record: any = {};
  i: any={};
  courses: Observable<any>;
  dayOfWeekEnum: SFSchemaEnumType[] = [
    {label: '周一', value: '周一'},
    {label: '周二', value: '周二'},
    {label: '周三', value: '周三'},
    {label: '周四', value: '周四'},
    {label: '周五', value: '周五'},
  ];

  timeOfDayEnum: SFSchemaEnumType[] = [
    {label: '08:00-09:45', value: '08:00-09:45'},
    {label: '10:15-12:00', value: '10:15-12:00'},
    {label: '14:00-15:45', value: '14:00-15:45'},
    {label: '16:15-18:00', value: '16:15-18:00'},
    {label: '19:00-20:45', value: '19:00-20:45'},
  ];

  schema: SFSchema = {
    properties: {
      course   : {title: '课程', type: 'number'},
      room     : {title: '教室', type: 'string'},
      dayOfWeek: {title: '周次', type: 'string', enum: this.dayOfWeekEnum},
      timeOfDay: {title: '时间', type: 'string', enum: this.timeOfDayEnum},
      capacity : {title: '容量', type: 'number', minimum: 0},
    },
    required  : ['course', 'room', 'dayOfWeek', 'timeOfDay', 'capacity'],
  };

  ui: SFUISchema = {
    '*'    : {
      spanLabelFixed: 100,
      grid          : {span: 12},
    },
    $course: {
      widget   : 'select',
      asyncData: () => this.courses,
    },
  };

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public http: _HttpClient,
  ) {
  }

  ngOnInit(): void {
    const url = `courses`;
    this.courses = this.http.get(url).pipe(map((data: any[]) => {
      let courses: SFSchemaEnumType[] = [];
      data.forEach(c => courses.push({label: c.courseName, value: c.id}));
      return courses;
    }), shareReplay());

    if (this.i) {
      this.i.course =  this.i.courseId;
    }
  }

  save(value: any) {
    if (value.course) {
      value.course = {id: value.course};
    }
    const url = `sections`;
    this.http.post(url, value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close() {
    this.modal.destroy();
  }
}
