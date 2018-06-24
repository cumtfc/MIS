package com.github.cumtfc.srs.service.section;

import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.teacher.Teacher;
import com.github.cumtfc.srs.po.user.SysUser;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface SectionService {

    /**
     * 根据id查section记录
     *
     * @param id section.id
     * @return section对象
     */
    Section findById(Integer id);

    /**
     * 查全部的section记录
     *
     * @return json格式
     */
    String findAll();

    /**
     * 保存一条section记录
     *
     * @param section 要保存的记录
     * @return 保存后的记录
     */
    Section saveOne(Section section);

    /**
     * 根据id删除一条记录
     *
     * @param id 要删除记录的id
     * @return 操作结果
     */
    boolean deleteOneById(Integer id);


    /**
     * 教师查自己选了的课
     *
     * @param sysUser 当前登陆用户
     * @return 结果json
     */
    String findMySections(SysUser sysUser);

    /**
     * 查询教师可选课列表
     *
     * @return 查询结果
     */
    String getTeacherSectionAvailable();

    /**
     * 查询学生可选课列表
     *
     * @return 查询结果
     */
    String getStudentSectionAvailable();

    /**
     * 教师新增一条选课记录
     *
     * @param section 当前要选的section
     * @param teacher 当前登陆的teacher
     * @return 保存后的section记录
     */
    Section chooseOneSection(Teacher teacher, Section section);

    /**
     * 老师退选一项Section
     *
     * @param sectionID 要退选的section的id
     * @return 操作结果
     */
    boolean unChooseOneSection(Integer sectionID);

}
