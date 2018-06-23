package com.github.cumtfc.srs.service.transcript;

import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.transcript.Transcript;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface TranscriptService {


    /**
     * 返回传入学生的所有选课记录，包括成绩单和选课状态
     * @param student 要查询的学生
     * @return json格式的Transcripts
     */
    String findAllByStudentJson(Student student);

    Transcript chooseOneSection(Student student, Section section);

}
