package com.github.cumtfc.srs.controller;

import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@RestController
@RequestMapping(value = "auth", produces = {APPLICATION_JSON_UTF8_VALUE})
public class AuthController {

    @GetMapping(value = "init",produces =  {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity init() throws JSONException {
        SysUser userDetails = (SysUser) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        String userName = "";
        JSONArray menus = new JSONArray();
        if (userDetails.getStudent()!=null) {
            userName = userDetails.getStudent().getName();
            JSONObject studyPlan = new JSONObject();
            studyPlan.put("text", "学习计划");
            studyPlan.put("link", "/student/study-plan");
            menus.put(studyPlan);
            JSONObject courseSelection = new JSONObject();
            courseSelection.put("text", "学生选课");
            courseSelection.put("link", "/student/course-selection");
            menus.put(courseSelection);
            JSONObject scoreQuery = new JSONObject();
            scoreQuery.put("text", "成绩查询");
            scoreQuery.put("link", "/student/score-query");
            menus.put(scoreQuery);
        }else if (userDetails.getTeacher()!=null){
            userName = userDetails.getTeacher().getName();
            JSONObject courses = new JSONObject();
            courses.put("text", "课程管理");
            courses.put("link", "/teacher/courses");
            menus.put(courses);
            JSONObject arrange = new JSONObject();
            arrange.put("text", "教师选课");
            arrange.put("link", "/teacher/arrange");
            menus.put(arrange);
            JSONObject statistics = new JSONObject();
            statistics.put("text", "选课统计");
            statistics.put("link", "/teacher/statistics");
            menus.put(statistics);
            JSONObject creditVerification = new JSONObject();
            creditVerification.put("text", "学分校验");
            creditVerification.put("link", "/teacher/creditVerification");
            menus.put(creditVerification);
        }

        JSONObject res = new JSONObject();
        res.put("user", userName);
        res.put("menu", menus);
        return ResponseEntity.ok(res.toString());
    }

    @PostMapping("login_success")
    public ResponseEntity loginPage() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("logout_success")
    public ResponseEntity logoutPage() {
        return ResponseEntity.ok().build();
    }

}
