package com.github.cumtfc.srs.dao;

import com.github.cumtfc.srs.po.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
