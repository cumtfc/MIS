package com.github.cumtfc.srs.controller;

import com.github.cumtfc.srs.po.section.Section;
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
    public ResponseEntity getAllSections(){
        return ResponseEntity.ok(sectionService.findAll());
    }

    @PostMapping(value = "")
    public ResponseEntity saveOneSection(@RequestBody Section section){
        return ResponseEntity.ok(sectionService.saveOne(section));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteOneSection(@PathVariable Integer id){
        return ResponseEntity.ok(sectionService.deleteOneById(id));
    }
}
