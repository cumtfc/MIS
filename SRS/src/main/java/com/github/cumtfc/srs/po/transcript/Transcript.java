package com.github.cumtfc.srs.po.transcript;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.cumtfc.srs.domain.specification.PrevCourseSpecification;
import com.github.cumtfc.srs.domain.specification.SelectOnceSpecification;
import com.github.cumtfc.srs.domain.specification.Specification;
import com.github.cumtfc.srs.domain.specification.StudyPlanSpecification;
import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.student.Student;

import javax.persistence.*;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Entity
public class Transcript {

    private Integer id;

    @JsonBackReference(value = "transcripts-section")
    private Section section;

    @JsonBackReference(value = "transcripts-student")
    private Student student;

    private Integer grade;

    private final Specification<Transcript> selectOnce = new SelectOnceSpecification();

    private final Specification<Transcript> prevCourse = new PrevCourseSpecification();

    private final Specification<Transcript> studyPlan = new StudyPlanSpecification();

    public Transcript() {
    }

    public Transcript(Section section, Student student) {
        this.section = section;
        this.student = student;
    }

    public String canChoose() {
        if (!selectOnce.isSatisfiedBy(this)) {
            return "{\"msg\":\"您已经选过这门课\"}";
        }
        if (!prevCourse.isSatisfiedBy(this)) {
            return "{\"msg\":\"您必须先修完所有先修课\"}";
        }
        if (!studyPlan.isSatisfiedBy(this)) {
            return "{\"msg\":\"选课不符合您的学习计划\"}";
        }
        return null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    public Section getSection() {
        return section;
    }


    public void setSection(Section section) {
        this.section = section;
    }

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
