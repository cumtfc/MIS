package com.github.cumtfc.srs.po.transcript;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    public Transcript() {
    }

    public Transcript(Section section, Student student) {
        this.section = section;
        this.student = student;
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
