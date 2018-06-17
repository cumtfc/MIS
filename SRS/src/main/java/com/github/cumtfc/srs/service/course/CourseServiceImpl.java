package com.github.cumtfc.srs.service.course;

import com.github.cumtfc.srs.dao.CourseRepository;
import com.github.cumtfc.srs.po.course.Course;
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
        System.out.println(courseRepository);
        return courseRepository.findAll();
    }

    @Override
    public Course saveOne(Course course) {
        return courseRepository.save(course);

    }

    @Override
    public List<Course> saveAll(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    @Override
    public boolean deleteOne(Course course) {
        courseRepository.delete(course);
        return true;
    }

    @Override
    public boolean deleteOne(Integer id) {
        courseRepository.deleteById(id);
        return true;
    }
}
