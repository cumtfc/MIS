package com.github.cumtfc.guitar.po.student;

import com.github.cumtfc.guitar.po.course.Course;
import com.github.cumtfc.guitar.po.arrange.courseArrange;
import com.github.cumtfc.guitar.po.selection.CourseSelection;

import javax.persistence.*;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"studentSn"}))
public class Student {

    private Integer id;

    private String name;

    private String studentSn;

    private String major;

    private String degree;

    private List<Course> studyPlan;

    private List<courseArrange> courseArranges;

    private List<CourseSelection> courseSelections;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentSn() {
        return studentSn;
    }

    public void setStudentSn(String studentSn) {
        this.studentSn = studentSn;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId",referencedColumnName = "courseId")
    public List<Course> getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(List<Course> studyPlan) {
        this.studyPlan = studyPlan;
    }

    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "teacher")
    public List<courseArrange> getCourseArranges() {
        return courseArranges;
    }

    public void setCourseArranges(List<courseArrange> courseArranges) {
        this.courseArranges = courseArranges;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "student")
    public List<CourseSelection> getCourseSelections() {
        return courseSelections;
    }

    public void setCourseSelections(List<CourseSelection> courseSelections) {
        this.courseSelections = courseSelections;
    }
}
