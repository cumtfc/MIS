package com.github.cumtfc.srs.controller;

import com.github.cumtfc.srs.service.section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
