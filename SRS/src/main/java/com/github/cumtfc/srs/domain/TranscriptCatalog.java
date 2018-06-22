package com.github.cumtfc.srs.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.transcript.Transcript;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Component
public class TranscriptCatalog {

   public String getTranscriptJson(List<Transcript> transcripts) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Transcript transcript : transcripts) {
            ObjectNode objectNode = mapper.convertValue(transcript, ObjectNode.class);
            objectNode.put("sectionSn", transcript.getSection().getSectionSn());
            objectNode.put("courseName", transcript.getSection().getCourse().getCourseName());
            objectNode.put("credit", transcript.getSection().getCourse().getCredit());
            objectNode.put("teacherName", transcript.getSection().getTeacher().getName());
            objectNode.put("room", transcript.getSection().getRoom());
            objectNode.put("dayOfWeek", transcript.getSection().getDayOfWeek());
            objectNode.put("timeOfDay", transcript.getSection().getTimeOfDay());
            if (transcript.getGrade()!=null) {
                objectNode.put("grade", transcript.getGrade());
            }else {
                objectNode.put("state", true);
            }
            arrayNode.add(objectNode);
        }
        return arrayNode.toString();
    }
}
