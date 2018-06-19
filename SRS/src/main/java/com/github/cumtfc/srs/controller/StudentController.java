package com.github.cumtfc.srs.controller;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */

import com.github.cumtfc.srs.bind.CurrentUser;
import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.user.SysUser;
import com.github.cumtfc.srs.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "students", produces = {APPLICATION_JSON_UTF8_VALUE})
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "studyPlans")
    public ResponseEntity getStudyPlans(@CurrentUser SysUser sysUser) {
        if (sysUser == null) {
            return ResponseEntity.badRequest().body("{'msg','无法获取当前登陆用户'}");
        } else if (sysUser.getStudent() == null) {
            return ResponseEntity.badRequest().body("{'msg','当前登陆用户不是学生'}");
        }
        Student student = studentService.findStudentById(sysUser.getStudent().getId());
        return ResponseEntity.ok(studentService.getStudyPlans(student));
    }


    @PostMapping(value = "studyPlans")
    public ResponseEntity addOneStudyPlanRecord(@RequestBody Course course,@CurrentUser SysUser sysUser) {
        if (sysUser == null) {
            return ResponseEntity.badRequest().body("{'msg','无法获取当前登陆用户'}");
        } else if (sysUser.getStudent() == null) {
            return ResponseEntity.badRequest().body("{'msg','当前登陆用户不是学生'}");
        }
        Student student = studentService.findStudentById(sysUser.getStudent().getId());
        return ResponseEntity.ok(studentService.addOneStudyPlanRecord(course,student));
    }

    @DeleteMapping(value = "studyPlans/{id}")
    public ResponseEntity deleteOneStudyPlanRecord(@PathVariable Integer id,@CurrentUser SysUser sysUser) {
        if (sysUser == null) {
            return ResponseEntity.badRequest().body("{'msg','无法获取当前登陆用户'}");
        } else if (sysUser.getStudent() == null) {
            return ResponseEntity.badRequest().body("{'msg','当前登陆用户不是学生'}");
        }
        Student student = studentService.findStudentById(sysUser.getStudent().getId());
        return ResponseEntity.ok(studentService.deleteOneStudentPlanRecord(id,student));
    }

}
