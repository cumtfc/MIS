package com.github.cumtfc.guitar.controller;

import com.github.cumtfc.guitar.service.courseService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@CrossOrigin
@RestController
@RequestMapping(value = "courses", produces = {APPLICATION_JSON_UTF8_VALUE})
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(value = "")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(courseService.findAll());
    }

}
