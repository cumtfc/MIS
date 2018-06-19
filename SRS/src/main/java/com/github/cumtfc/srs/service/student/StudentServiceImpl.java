package com.github.cumtfc.srs.service.student;

import com.github.cumtfc.srs.dao.StudentRepository;
import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Course> getStudyPlans(Student student) {
        if (student != null) {
            Optional<Student> studentInRepo = studentRepository.findById(student.getId());
            if (studentInRepo.isPresent()) {
                student = studentInRepo.get();
                return student.getStudyPlan();
            }
        }
        return new ArrayList<>(0);
    }

    @Override
    public List<Course> addOneStudyPlanRecord(Course course, Student student) {
        student.getStudyPlan().add(course);
        studentRepository.save(student);
        return student.getStudyPlan();
    }

    @Override
    public List<Course> deleteOneStudentPlanRecord(Integer courseId,Student student){
        student.getStudyPlan().removeIf(course -> course.getId().equals(courseId));
        studentRepository.save(student);
        return student.getStudyPlan();
    }

    @Override
    public Student findStudentById(Integer id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.orElse(null);
    }
}
