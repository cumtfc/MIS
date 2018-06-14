package com.github.cumtfc.guitar.po.course;

import com.github.cumtfc.guitar.po.arrange.courseArrange;

import javax.persistence.*;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"courseSn"}))
public class Course {

    private Integer id;

    private String courseSn;

    private String courseName;

    private Integer credit;

    private List<Course> prevCourses;

    private List<courseArrange> courseArranges;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "prevCourseId",referencedColumnName = "id")
    public List<Course> getPrevCourses() {
        return prevCourses;
    }

    public void setPrevCourses(List<Course> prevCourses) {
        this.prevCourses = prevCourses;
    }

    public String getCourseSn() {
        return courseSn;
    }

    public void setCourseSn(String courseSn) {
        this.courseSn = courseSn;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "course")
    public List<courseArrange> getCourseArranges() {
        return courseArranges;
    }

    public void setCourseArranges(List<courseArrange> courseArranges) {
        this.courseArranges = courseArranges;
    }
}
