package com.github.cumtfc.srs.controller;

import com.github.cumtfc.srs.bind.CurrentUser;
import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.user.SysUser;
import com.github.cumtfc.srs.service.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@RestController
@RequestMapping(value = "sections", produces = {APPLICATION_JSON_UTF8_VALUE})
public class SectionController {

    @Autowired
    SectionService sectionService;

    @GetMapping(value = "")
    public ResponseEntity getAllSections() {
        return ResponseEntity.ok(sectionService.findAll());
    }

    @GetMapping(value = "my")
    public ResponseEntity getMySections(@CurrentUser SysUser sysUser) {

        return ResponseEntity.ok(sectionService.findMySections(sysUser));
    }

    @GetMapping("teacher/available")
    public ResponseEntity getTeacherAvailableSections() {
        return ResponseEntity.ok(sectionService.getTeacherSectionAvailable());
    }

    @GetMapping(value = "student/available")
    public ResponseEntity getStudentAvailableSections(){
        return ResponseEntity.ok(sectionService.getStudentSectionAvailable());
    }

    @PostMapping(value = "choose")
    public ResponseEntity chooseSection(@CurrentUser SysUser sysUser, @RequestBody Section section) {
        return ResponseEntity.ok(sectionService.chooseOneSection(sysUser.getTeacher(), section));
    }

    @PostMapping(value = "")
    public ResponseEntity saveOneSection(@RequestBody Section section) {
        return ResponseEntity.ok(sectionService.saveOne(section));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteOneSection(@PathVariable Integer id) {
        return ResponseEntity.ok(sectionService.deleteOneById(id));
    }

    @DeleteMapping(value = "my/{id}")
    public ResponseEntity deleteOneSelectedSection(@PathVariable Integer id) {
        return ResponseEntity.ok(sectionService.unChooseOneSection(id));
    }

}
