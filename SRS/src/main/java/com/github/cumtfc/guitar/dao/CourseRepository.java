package com.github.cumtfc.guitar.dao;

import com.github.cumtfc.guitar.po.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface CourseRepository extends JpaRepository<Course,Integer> {

}
