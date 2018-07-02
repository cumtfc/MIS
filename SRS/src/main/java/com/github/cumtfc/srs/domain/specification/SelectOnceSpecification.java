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
public class SelectOnceSpecification implements Specification<Transcript> {

    @Override
    public boolean isSatisfiedBy(Transcript transcript) {
        List<Transcript> transcripts = transcript.getStudent().getTranscripts();
        //两种情况，一种是选了还没出成绩，一种是选了，已经及格了，都不许再选了
        Set<Course> courseSet = transcripts.stream()
            .filter(t -> t.getGrade() == null || t.getGrade() >= 60)
            .map(t -> t.getSection().getCourse())
            .collect(Collectors.toSet());
        return !courseSet.contains(transcript.getSection().getCourse());
    }
}
