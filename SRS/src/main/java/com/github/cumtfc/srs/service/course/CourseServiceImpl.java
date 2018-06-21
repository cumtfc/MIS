package com.github.cumtfc.srs.service.course;

import com.github.cumtfc.srs.dao.CourseRepository;
import com.github.cumtfc.srs.domain.CourseCatalog;
import com.github.cumtfc.srs.po.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseCatalog catalog;

    @Override
    public String findAllInJson() {
        List<Course> courses = courseRepository.findAll();
        return catalog.toJSON(courses);
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
