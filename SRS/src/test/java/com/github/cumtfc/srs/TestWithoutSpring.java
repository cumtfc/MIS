package com.github.cumtfc.srs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.course.Course;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public class TestWithoutSpring {

    @Test
    public void testJackson() {
        ObjectMapper mapper = new ObjectMapper();
        List<Course> list = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        Course course2 = new Course();
        course.setId(2);
        list.add(course);
        list.add(course2);
        try {
            JsonNode jsonNode = mapper.convertValue(list, ArrayNode.class);
            for (JsonNode node : jsonNode) {
                ObjectNode objectNode = (ObjectNode) node;
                objectNode.put("extraData", "helloWorld");
            }
            mapper.writeValue(System.out, jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}