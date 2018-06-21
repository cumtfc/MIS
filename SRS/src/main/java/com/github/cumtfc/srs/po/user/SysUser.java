package com.github.cumtfc.srs.po.user;

import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.teacher.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Entity
public class SysUser implements UserDetails,Serializable {

    private Integer id;

    private String username;

    private String password;

    private Student student;

    private Teacher teacher;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>(1);
        if (this.getTeacher()!= null) {
            list.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
        } else if (this.getStudent()!=null) {
            list.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "user")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "user")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
