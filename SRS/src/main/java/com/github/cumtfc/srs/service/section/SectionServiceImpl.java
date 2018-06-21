package com.github.cumtfc.srs.service.section;

import com.github.cumtfc.srs.dao.SectionRepository;
import com.github.cumtfc.srs.domain.SectionCatalog;
import com.github.cumtfc.srs.po.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    SectionCatalog catalog;

    @Override
    public String findAll() {
        List<Section> all = sectionRepository.findAll();
        return catalog.toJSON(all);
    }
}
