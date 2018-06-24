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

    /**
     * 选一门课
     * @param student 选课学生
     * @param section 要选的section
     * @return 操作结果json格式形如@example:`{'msg':'操作成功'}`
     */
    String chooseOneSection(Student student, Section section);

    boolean unChooseOneSection(Integer id);

}
