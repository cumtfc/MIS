package com.github.cumtfc.srs.controller;

import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@RestController
@RequestMapping(value = "courses", produces = {APPLICATION_JSON_UTF8_VALUE})
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(value = "")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(courseService.findAllInJson());
    }

    @PostMapping(value = "", consumes = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity addOne(@RequestBody Course course){
        return ResponseEntity.ok(courseService.saveOne(course));
    }

    @PutMapping(value = "", consumes = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity updateOne(@RequestBody Course course){
        return ResponseEntity.ok(courseService.saveOne(course));
    }

    @DeleteMapping(value = "")
    public ResponseEntity deleteOne(@RequestBody Course course){
        return ResponseEntity.ok(courseService.deleteOne(course));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteOneById(@PathVariable Integer id){
        return ResponseEntity.ok(courseService.deleteOne(id));
    }
}
