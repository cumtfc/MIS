package com.github.cumtfc.srs.component;

import com.github.cumtfc.srs.component.specification.PrevCourseSpecification;
import com.github.cumtfc.srs.component.specification.SelectOnceSpecification;
import com.github.cumtfc.srs.component.specification.Specification;
import com.github.cumtfc.srs.component.specification.StudyPlanSpecification;
import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.transcript.Transcript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Component
public class TranscriptFactory {

    @Resource(type = SelectOnceSpecification.class)
    private Specification<Transcript> selectOnce;

    @Resource(type = PrevCourseSpecification.class)
    private Specification<Transcript> prevCourse;

    @Resource(type = StudyPlanSpecification.class)
    private Specification<Transcript> studyPlan;

    public Transcript getTranscript(Student student, Section section) {
        Transcript transcript = new Transcript(section, student);
        if (!selectOnce.isSatisfiedBy(transcript)) {
            return null;
        }
        if (!prevCourse.isSatisfiedBy(transcript)) {
            return null;
        }
        if (!studyPlan.isSatisfiedBy(transcript)) {
            return null;
        }
        return transcript;
    }
}
