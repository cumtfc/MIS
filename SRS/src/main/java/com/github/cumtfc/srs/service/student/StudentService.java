package com.github.cumtfc.srs.service.student;

import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.user.SysUser;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface StudentService {

    /**
     * 获取学习计划
     * @param student：学生
     * @return 学习计划中的课程列表
     */
    List<Course> getStudyPlans(Student student);

    /**
     * 根据id查询学生
     *
     * @param id 学生记录主键
     * @return 学生OR null
     */
    public Student findStudentById(Integer id);
}
