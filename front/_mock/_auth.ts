import {environment} from "@env/environment";

const prefiex: string = environment.SERVER_URL;
const menu = [
  {text: '学生选课', link: '/student/course-selection', icon: 'anticon anticon-appstore-o'},
  {text: '学习计划', link: '/student/study-plan', icon: 'anticon anticon-appstore-o'},
  {text: '课程管理', link: '/teacher/courses', icon: 'anticon anticon-appstore-o'},
  {text: '教务排课', link: '/teacher/arrange', icon: 'anticon anticon-appstore-o'}];
export const AUTH = {
  'GET auth/init': {user: '冯楚', menu:menu},
};
