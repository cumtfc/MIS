package com.github.cumtfc.srs.service.auth;

import com.github.cumtfc.srs.po.user.SysUser;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface AuthService {

    /**
     * 页面初始化的数据
     * @param sysUser 当前用户
     * @return json化后的数据
     */
    String init(SysUser sysUser);

}
