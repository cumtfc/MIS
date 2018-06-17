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
        userDetails.getUsername();
        JSONObject res = new JSONObject();
        res.put("user", userDetails.getUsername());
        JSONArray menus = new JSONArray();
        JSONObject menu = new JSONObject();
        menu.put("text", "课程管理");
        menu.put("link", "/courses");
        menus.put(menu);
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
