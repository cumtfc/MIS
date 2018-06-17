package com.github.cumtfc.srs.application;

import com.github.cumtfc.srs.dao.SysUserRepository;
import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    SysUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("s:"+s);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
    }
}
