package com.github.cumtfc.srs.service.section;

import com.github.cumtfc.srs.po.section.Section;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface SectionService {

    /**
     * 查全部的section记录
     * @return json格式
     */
    String findAll();

    /**
     * 保存一条section记录
     * @param  section 要保存的记录
     * @return 保存后的记录
     */
    Section saveOne(Section section);

    /**
     * 根据id删除一条记录
     * @param id 要删除记录的id
     * @return 操作结果
     */
    boolean deleteOneById(Integer id);

}
