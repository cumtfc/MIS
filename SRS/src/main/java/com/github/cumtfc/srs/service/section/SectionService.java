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

}
