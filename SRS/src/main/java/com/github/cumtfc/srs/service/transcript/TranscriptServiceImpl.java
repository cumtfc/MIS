package com.github.cumtfc.srs.service.transcript;

import com.github.cumtfc.srs.dao.SysUserRepository;
import com.github.cumtfc.srs.dao.TranscriptRepository;
import com.github.cumtfc.srs.domain.TranscriptCatalog;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class TranscriptServiceImpl implements TranscriptService {

    @Autowired
    TranscriptRepository transcriptRepository;

    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    TranscriptCatalog catalog;

    @Override
    public String findAllByStudentJson(Student student) {

        return catalog.getTranscriptJson(transcriptRepository.getTranscriptsByStudentEquals(student));
    }
}
