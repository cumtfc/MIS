package com.github.cumtfc.srs.dao;

import com.github.cumtfc.srs.po.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface SectionRepository extends JpaRepository<Section,Integer> {

    List<Section> findSectionsByTeacherNull();
}
