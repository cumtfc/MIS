import {Component, OnInit, ViewChild} from '@angular/core';
import {NzMessageService, NzModalRef} from 'ng-zorro-antd';
import {_HttpClient} from '@delon/theme';
import {SimpleTableColumn, SimpleTableComponent} from "@delon/abc";

@Component({
  selector   : 'app-teacher-section-view',
  templateUrl: './view.component.html',
  styleUrls  : ['./view.component.less']
})
export class TeacherSectionViewComponent implements OnInit {
  record: any = {};
  i: any;
  params: any = {};
  dataSet: any[] = [];
  editCache: any = {};
  @ViewChild('st') st: SimpleTableComponent;
  columns: SimpleTableColumn[] = [
    {title: '姓名', index: 'name'},
    {title: '学号', index: 'studentSn'},
    {title: '专业', index: 'major'},
    {title: '年级', index: 'degree'},
    {title: '成绩', index: 'grade', render: 'custom'},
  ];

  constructor(
    private modal: NzModalRef,
    public msgSrv: NzMessageService,
    public http: _HttpClient
  ) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    const url = `transcripts/teacher/${this.record.id}`;
    this.http.get(url).subscribe((data: any) => {
      this.dataSet = data;
      this.updateEditCache();
    });
  }

  startEdit(id: number): void {
    this.editCache[id].edit = true;
  }

  finishEdit(id: number): void {
    this.editCache[id].edit = false;
    this.dataSet.find(item => item.id === id).grade = this.editCache[id].grade;
  }

  updateEditCache(): void {
    this.dataSet.forEach(item => {
      if (!this.editCache[item.id]) {
        this.editCache[item.id] = {
          edit : false,
          grade: item.grade
        };
      }
    });
  }

  saveAll() {
    const url = `transcripts/teacher`;
    this.http.post(url, this.dataSet).subscribe((res: any) => {
      this.dataSet = res;
      this.close();
    })
  }

  close() {
    this.modal.destroy();
  }
}
