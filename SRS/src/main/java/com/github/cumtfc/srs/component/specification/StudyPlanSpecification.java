package com.github.cumtfc.srs.component.specification;


import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.po.transcript.Transcript;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Component
public class StudyPlanSpecification implements Specification<Transcript> {

    @Override
    public boolean isSatisfiedBy(Transcript transcript) {
        List<Course> studyPlan = transcript.getStudent().getStudyPlan();
        Course course = transcript.getSection().getCourse();
        return studyPlan.stream().anyMatch(c -> c.getId().equals(course.getId()));
    }
}
