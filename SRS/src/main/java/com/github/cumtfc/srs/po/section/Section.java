package com.github.cumtfc.srs.po.section;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.po.transcript.Transcript;
import com.github.cumtfc.srs.po.teacher.Teacher;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"sectionSn"}))
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Section implements Serializable {

    private Integer id;

    private String sectionSn;

    private String dayOfWeek;

    private String timeOfDay;

    private String room;

    private Integer capacity;

    @JsonBackReference(value = "course-section")
    private Course course;
    @JsonBackReference(value = "teacher-section")
    private Teacher teacher;

    @JsonManagedReference(value = "transcripts-section")
    private List<Transcript> transcripts;

    public void generateSectionSn(){
        if (getSectionSn() == null) {
            setSectionSn(UUID.randomUUID().toString().substring(0,8));
        }
    }


    public String transcriptBySectionJson(){
        List<Transcript> transcripts = this.getTranscripts();
        if (transcripts.size() > this.getCapacity()) {
            transcripts = transcripts.subList(0, this.getCapacity());
        }
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Transcript transcript : transcripts) {
            arrayNode.add(transcript.toJson());
        }
        return arrayNode.toString();

    }

    public ObjectNode toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.convertValue(this, ObjectNode.class);
        objectNode.put("capacityWithFraction", this.capacityLeft() + "/" + this.getCapacity());
        return objectNode;
    }


    public int capacityLeft(){
        int size = this.getTranscripts().size();
        int left = this.getCapacity() - size;
        if (left < 0) {
            left = 0;
        }
        return left;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "section")
    public List<Transcript> getTranscripts() {
        return transcripts;
    }

    public void setTranscripts(List<Transcript> transcripts) {
        this.transcripts = transcripts;
    }

    public String getSectionSn() {
        return sectionSn;
    }

    public void setSectionSn(String sectionSn) {
        this.sectionSn = sectionSn;
    }
}
