package com.github.cumtfc.srs;

import com.github.cumtfc.srs.application.DemoApplication;
import com.github.cumtfc.srs.dao.CourseRepository;
import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.service.course.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Rollback(value = false)
@Transactional
public class DemoApplicationTests {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void testSaveCourse() {
        Course course = courseRepository.findById(8).get();
        List<Course> list = new ArrayList<>();
        Course course1 = new Course();
        course1.setId(1);
        list.add(course1);
        course.setPrevCourses(list);
        courseService.saveOne(course);
    }

}
