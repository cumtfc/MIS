package com.github.cumtfc.srs.service.user;

import com.github.cumtfc.srs.dao.SysUserRepository;
import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser refreshSysUser(SysUser sysUser) {
        Optional<SysUser> userOptional = sysUserRepository.findById(sysUser.getId());
        return userOptional.orElse(null);
    }
}
