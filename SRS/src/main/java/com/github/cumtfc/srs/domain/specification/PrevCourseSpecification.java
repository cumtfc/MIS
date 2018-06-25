package com.github.cumtfc.srs.domain.specification;

import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.po.transcript.Transcript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Component
public class PrevCourseSpecification implements Specification<Transcript> {

    @Override
    public boolean isSatisfiedBy(Transcript transcript) {
        List<Transcript> transcripts = transcript.getStudent().getTranscripts();
        List<Course> prevCourses = transcript.getSection().getCourse().getPrevCourses();
        //如果没成绩或没及格视为没学过
        Set<Course> courseSet = transcripts.stream()
            .filter(t -> t.getGrade() != null && t.getGrade() >= 60)
            .map(t -> t.getSection().getCourse())
            .collect(Collectors.toSet());
        return (courseSet.containsAll(prevCourses));
    }
}
