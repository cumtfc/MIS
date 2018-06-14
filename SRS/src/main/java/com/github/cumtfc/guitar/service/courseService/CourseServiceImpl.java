package com.github.cumtfc.guitar.service.courseService;

import com.github.cumtfc.guitar.dao.CourseRepository;
import com.github.cumtfc.guitar.po.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

}
