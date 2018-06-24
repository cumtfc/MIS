package com.github.cumtfc.srs.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.course.Course;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public class CourseCatalog {

    private static final CourseCatalog INSTANCE = new CourseCatalog();

    public String toJSON(List<Course> courses) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Course course : courses) {
            ObjectNode objectNode = mapper.convertValue(course, ObjectNode.class);
            StringBuilder preString = new StringBuilder();
            for (Course pre : course.getPrevCourses()) {
                preString.append(pre.getCourseName()).append(",");
            }
            objectNode.put("prevCoursesString", preString.toString());
            arrayNode.add(objectNode);
        }
        return arrayNode.toString();
    }

    public static CourseCatalog getInstance() {
        return INSTANCE;
    }

    private CourseCatalog() {
    }

}
