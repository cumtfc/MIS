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
     * 添加一条学习计划记录
     * @param course 要添加的课程
     * @param student 操作学生
     * @return 新的学习计划
     */
    List<Course> addOneStudyPlanRecord(Course course,Student student);

    /**
     * 删除一条学习计划记录
     * @param courseId 要删除的课程id
     * @param student 操作学生
     * @return 新的学习计划
     */
    public List<Course> deleteOneStudentPlanRecord(Integer courseId, Student student);

    /**
     * 根据id查询学生
     *
     * @param id 学生记录主键
     * @return 学生OR null
     */
    public Student findStudentById(Integer id);
}
