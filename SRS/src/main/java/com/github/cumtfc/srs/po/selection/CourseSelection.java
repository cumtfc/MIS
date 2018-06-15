package com.github.cumtfc.srs.po.selection;

import com.github.cumtfc.srs.po.arrange.CourseArrange;
import com.github.cumtfc.srs.po.student.Student;

import javax.persistence.*;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Entity
public class CourseSelection {

    private Integer id;

    private CourseArrange courseArrange;

    private Student student;

    private Integer grade;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    public CourseArrange getCourseArrange() {
        return courseArrange;
    }


    public void setCourseArrange(CourseArrange courseArrange) {
        this.courseArrange = courseArrange;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
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
