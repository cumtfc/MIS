package com.github.cumtfc.srs.service.course;

import com.github.cumtfc.srs.dao.CourseRepository;
import com.github.cumtfc.srs.po.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    @Transactional(rollbackFor = Exception.class)
    public Course saveOne(Course course) {
//        for (int i = 0; i < course.getPrevCourses().size(); i++) {
//
//            Optional<Course> coursePo = courseRepository.findById(course.getPrevCourses().get(i).getId());
//            if (coursePo.isPresent()) {
//                course.getPrevCourses().set(i, coursePo.get());
//            }
//        }
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
