package com.github.cumtfc.srs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.course.Course;
import sun.applet.Main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public class TestWithoutSpring {

    public static void main(String[] args) {
        TestWithoutSpring main = new TestWithoutSpring();
        main.testAll();
    }

    private void testAll(){
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.getParameterCount()>0) {
                System.out.println("测试方法必须无参");
                continue;
            }
            try {
                method.invoke(this);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void testJackson(){
        ObjectMapper mapper = new ObjectMapper();
        List<Course> list = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        Course course2 = new Course();
        course.setId(2);
        list.add(course);
        list.add(course2);
        try {
            JsonNode jsonNode = mapper.convertValue(list,ArrayNode.class);
            for (JsonNode node : jsonNode) {
                ObjectNode objectNode = (ObjectNode) node;
                objectNode.put("extraData", "helloWorld");
            }
            mapper.writeValue(System.out,jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}