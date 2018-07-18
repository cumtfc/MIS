package com.github.cumtfc.srs.po.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.section.Section;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"courseSn"}))
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Course implements Serializable {

    private Integer id;

    private String courseSn;

    private String courseName;

    private Integer credit;

    private List<Course> prevCourses;

    @JsonManagedReference(value = "course-section")
    private List<Section> sections;

    public String getSectionsJson(List<Section> sections) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Section section : sections) {
            ObjectNode objectNode = section.toJson();
            objectNode.setAll(section.getCourse().toJson());
            arrayNode.add(objectNode);
        }
        return arrayNode.toString();
    }

    public ObjectNode toJson() {
        ObjectNode objectNode = new ObjectMapper().convertValue(this, ObjectNode.class);
        StringBuilder preString = new StringBuilder();
        for (Course pre : this.getPrevCourses()) {
            preString.append(pre.getCourseName()).append(",");
        }
        objectNode.put("prevCoursesString", preString.toString());
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

    @ManyToMany(cascade={CascadeType.DETACH},fetch = FetchType.LAZY)
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

    @OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY,mappedBy = "course")
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", courseSn='" + courseSn + '\'' +
            ", courseName='" + courseName + '\'' +
            ", credit=" + credit +
            ", prevCourses=" + prevCourses +
            ", sections=" + sections +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }

        Course course = (Course) o;

        if (!getId().equals(course.getId())) {
            return false;
        }
        return getCourseSn().equals(course.getCourseSn());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCourseSn().hashCode();
        return result;
    }
}
