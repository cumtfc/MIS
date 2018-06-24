package com.github.cumtfc.srs.controller;

import com.github.cumtfc.srs.bind.CurrentUser;
import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.user.SysUser;
import com.github.cumtfc.srs.service.section.SectionService;
import com.github.cumtfc.srs.service.transcript.TranscriptService;
import com.github.cumtfc.srs.service.user.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@RestController
@RequestMapping(value = "transcripts", produces = {APPLICATION_JSON_UTF8_VALUE})
public class TranscriptController {
    @Autowired
    TranscriptService transcriptService;
    @Autowired
    SectionService sectionService;
    @Autowired
    SysUserService sysUserService;

    @GetMapping(value = "my")
    public ResponseEntity getMyTranscripts(@CurrentUser SysUser sysUser){
        sysUser = sysUserService.refreshSysUser(sysUser);
        return ResponseEntity.ok(transcriptService.findAllByStudentJson(sysUser.getStudent()));
    }

    @PostMapping(value = "")
    public ResponseEntity chooseOneSection(@CurrentUser SysUser sysUser,@RequestBody Section section){
        sysUser = sysUserService.refreshSysUser(sysUser);
        section = sectionService.findById(section.getId());
        return ResponseEntity.ok(transcriptService.chooseOneSection(sysUser.getStudent(),section));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity unChooseOneSection(@PathVariable Integer id){
        return ResponseEntity.ok(transcriptService.unChooseOneSection(id));
    }

}
