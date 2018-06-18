import {Component, OnInit, ViewChild} from '@angular/core';
import {NzMessageService, NzModalRef} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {SFSchema, SFUISchema} from '@delon/form';
import {of} from 'rxjs';
import {delay} from 'rxjs/operators';
import {SFComponent} from "@delon/form/src/src/sf.component";

@Component({
  selector   : 'app-teacher-course-edit',
  templateUrl: './edit.component.html',
})
export class TeacherCourseEditComponent implements OnInit {
  record: any = {};
  i: any = {};
  courses: any[];
  coursesSelection: any[] = [];
  schema: SFSchema = {
    properties: {
      courseSn   : {type: 'string', title: '编号'},
      courseName : {type: 'string', title: '课程名', maxLength: 15},
      credit     : {type: 'number', title: '学分'},
      prevCourses: {type: 'number', title: '先修课程'}
    },
    required  : ['courseSn', 'courseName', 'credit'],
  };

  ui: SFUISchema = {
    '*'         : {
      spanLabelFixed: 100,
      grid          : {span: 12},
    },
    $credit     : {
      widget: 'number',
      grid  : {span: 12},
    },
    $prevCourses: {
      widget   : 'select',
      mode     : 'multiple',
      asyncData: () => of(this.courses).pipe(delay(500))
    }
  };

  @ViewChild('sf')
  private sf: SFComponent;

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public http: _HttpClient,
  ) {
  }

  ngOnInit(): void {
    this.courses = this.courses.map(course => {
      return {label: course.courseName, value: course.id}
    });

    if (this.record && this.record.prevCourses) {
      this.record.prevCourses = this.record.prevCourses.map(course => {
        return course.id;
      });
    }
  }

  save(value: any) {
    if (value.prevCourses) {
      let pre: any[] = value.prevCourses;
      value.prevCourses=pre.map(p => {
        return {id: p};
      });
    }
    const url = `courses`;
    this.http.post(url, value).subscribe(res => {
      this.msgSrv.success('保存成功');
      this.modal.close(true);
    });
  }

  close() {
    this.modal.destroy();
  }
}
