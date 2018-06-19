import {Component, OnInit, ViewChild} from '@angular/core';
import {_HttpClient, ModalHelper} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from '@delon/abc';
import {SFSchema} from '@delon/form';
import {StudentStudyPlanEditComponent} from "./edit/edit.component";
import {filter} from "rxjs/operators";

@Component({
  selector   : 'app-student-study-plan',
  templateUrl: './study-plan.component.html',
})
export class StudentStudyPlanComponent implements OnInit {

  params: any = {};
  dataSet: any = [];
  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    {title: '课程编号', index: 'courseSn'},
    {title: '课程名', index: 'courseName'},
    {title: '学分',index: 'credit'},
    {
      title  : '',
      buttons: [
        { text: '删除', type: 'del',click: (record)=>this.delete(record) },
      ]
    }
  ];

  constructor(private http: _HttpClient, private modal: ModalHelper) {
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    const url = `students/studyPlans`;
    this.http.get(url).subscribe((data: any) => {
      this.dataSet = data;
    });
  }

  delete(record) {
    const url = `students/studyPlans/${record.id}`;
    this.http.delete(url).subscribe((data)=>{
      this.dataSet = data;
      // this.reloadData();
    })
  }

  edit() {
    this.modal
      .static(StudentStudyPlanEditComponent)
      .pipe(filter(w => w === true))
      .subscribe(() => this.reloadData());
  }

}
