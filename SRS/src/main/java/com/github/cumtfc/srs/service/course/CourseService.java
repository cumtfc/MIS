package com.github.cumtfc.srs.service.course;

import com.github.cumtfc.srs.po.course.Course;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface CourseService {

    /**
     * 获取全部课程JSON
     * @return List<Course>
     */
    String findAllInJson();


    /**
     * 保存一个课程
     * @param course 要保存的课程
     * @return 已保存的课程
     */
    Course saveOne(Course course);

    /**
     * 保存所给全部课程
     * @param courses 要保存的课程新值
     * @return 已保存的课程
     */
    List<Course> saveAll(List<Course> courses);

    /**
     * 删除一个课程
     * @param course 要删除的课程
     * @return 操作结果
     */
    boolean deleteOne(Course course);

    /**
     * 根据id删除一个课程
     * @param id 要删除的课程id
     * @return 操作结果
     */
    boolean deleteOne(Integer id);
}
