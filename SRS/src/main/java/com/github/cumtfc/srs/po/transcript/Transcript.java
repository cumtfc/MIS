package com.github.cumtfc.srs.po.transcript;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
            return "您已经选过这门课";
        }
        if (!prevCourse.isSatisfiedBy(this)) {
            return "您必须先修完所有先修课";
        }
        if (!studyPlan.isSatisfiedBy(this)) {
            return "选课不符合您的学习计划";
        }
        return null;
    }

    public String state(){
        if (this.getGrade() != null) {
            return "已选";
        }
        int index = this.getSection().getTranscripts().indexOf(this);
        Integer capacity = this.getSection().getCapacity();
        if (index < capacity) {
            return  "已选";
        }else {
            return "队列中";
        }
    }

    public ObjectNode toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.convertValue(this, ObjectNode.class);
        objectNode.put("sectionSn", this.getSection().getSectionSn());
        objectNode.put("courseName", this.getSection().getCourse().getCourseName());
        objectNode.put("credit", this.getSection().getCourse().getCredit());
        objectNode.put("teacherName", this.getSection().getTeacher().getName());
        objectNode.put("room", this.getSection().getRoom());
        objectNode.put("dayOfWeek", this.getSection().getDayOfWeek());
        objectNode.put("timeOfDay", this.getSection().getTimeOfDay());
        if (this.getGrade() != null) {
            objectNode.put("grade", this.getGrade());
        }
        //这部分学生信息可以考虑不在这里序列化，结合业务场景
        objectNode.put("state", this.state());
        objectNode.put("name", this.getStudent().getName());
        objectNode.put("studentSn", this.getStudent().getStudentSn());
        objectNode.put("major", this.getStudent().getMajor());
        objectNode.put("degree", this.getStudent().getDegree());
        ObjectNode student = mapper.createObjectNode();
        student.put("id", this.getStudent().getId());
        objectNode.set("student", student);
        ObjectNode section = mapper.createObjectNode();
        section.put("id", this.getSection().getId());
        objectNode.set("section", section);
        return objectNode;
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
