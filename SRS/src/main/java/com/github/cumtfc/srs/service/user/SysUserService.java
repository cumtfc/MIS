package com.github.cumtfc.srs.service.user;

import com.github.cumtfc.srs.dao.SysUserRepository;
import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface SysUserService {

    SysUser refreshSysUser(SysUser sysUser);


}
