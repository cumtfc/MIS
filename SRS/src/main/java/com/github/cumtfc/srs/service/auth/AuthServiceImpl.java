package com.github.cumtfc.srs.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.domain.Menu;
import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    Menu menu;

    @Override
    public String init(SysUser sysUser) {
        String userName = "";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        if (sysUser.getStudent() != null) {
            userName = sysUser.getStudent().getName();
        } else if (sysUser.getTeacher() != null) {
            userName = sysUser.getTeacher().getName();
        }
        List<Menu.MenuItem> menus = menu.getMenus(sysUser);
        res.put("user", userName);
        res.set("menu", mapper.valueToTree(menus));
        return res.toString();
    }
}
