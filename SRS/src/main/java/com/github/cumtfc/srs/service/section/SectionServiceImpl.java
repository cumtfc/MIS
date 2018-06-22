package com.github.cumtfc.srs.service.section;

import com.github.cumtfc.srs.dao.SectionRepository;
import com.github.cumtfc.srs.domain.SectionCatalog;
import com.github.cumtfc.srs.po.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Override
    public Section saveOne(Section section) {
        if (section.getSectionSn()==null) {
            section.setSectionSn(UUID.randomUUID().toString());
        }
        return sectionRepository.save(section);
    }

    @Override
    public boolean deleteOneById(Integer id) {
        sectionRepository.deleteById(id);
        return true;
    }
}
