package com.github.cumtfc.srs.service.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.dao.CourseRepository;
import com.github.cumtfc.srs.po.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ArrayNode findAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<Course> courses = courseRepository.findAll();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Course course : courses) {
            ObjectNode objectNode = mapper.convertValue(course, ObjectNode.class);
            StringBuilder preString = new StringBuilder();
            for (Course pre : course.getPrevCourses()) {
                preString.append(pre.getCourseName()).append(";");
            }
            objectNode.put("prevCoursesString", preString.toString());
            arrayNode.add(objectNode);
        }
        return arrayNode;
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
