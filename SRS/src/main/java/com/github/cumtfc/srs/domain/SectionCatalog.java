package com.github.cumtfc.srs.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.section.Section;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Component
public class SectionCatalog {
    public String toJSON(List<Section> sections) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Section section : sections) {
            ObjectNode objectNode = mapper.convertValue(section, ObjectNode.class);
            arrayNode.add(objectNode);
        }
        return arrayNode.toString();
    }
}
