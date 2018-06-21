package com.github.cumtfc.srs.controller;

import com.github.cumtfc.srs.bind.CurrentUser;
import com.github.cumtfc.srs.po.user.SysUser;
import com.github.cumtfc.srs.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@RestController
@RequestMapping(value = "auth", produces = {APPLICATION_JSON_UTF8_VALUE})
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping(value = "init", produces = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity init(@CurrentUser SysUser user) throws JSONException {
        return ResponseEntity.ok(authService.init(user));
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
