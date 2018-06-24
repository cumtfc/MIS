package com.github.cumtfc.srs.service.transcript;

import com.github.cumtfc.srs.dao.SysUserRepository;
import com.github.cumtfc.srs.dao.TranscriptRepository;
import com.github.cumtfc.srs.domain.TranscriptCatalog;
import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.transcript.Transcript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Service
public class TranscriptServiceImpl implements TranscriptService {

    @Autowired
    TranscriptRepository transcriptRepository;
    @Autowired
    SysUserRepository sysUserRepository;

    private final TranscriptCatalog catalog = TranscriptCatalog.getInstance();

    @Override
    public String findAllByStudentJson(Student student) {
        return catalog.getTranscriptJson(transcriptRepository.getTranscriptsByStudentEquals(student));
    }

    /**
     * 选课的场景类似秒杀，除了waitingList的实现外，还需要注意"超卖"问题 因为高并发，所以显然不能直接加锁. 经典的解决方案是带标记地更新库存容量
     * 因为本身涉及waitingList，所以我这里的解决方案是只要过了选课校验就保存当前记录。然后返回等待结果同步的提示信息，前端刷新数据，此时此条选课记录的index如果小于容量，则选课成功
     * 否则直接视为在waitingList中，当前面有人退选时，记录删除，则后面的记录自然往上补齐。选课通道关闭时统一清除冗余的记录，并通知选课成功的人付费
     */
    @Override
    public String chooseOneSection(Student student, Section section) {
        Transcript transcript = new Transcript(section, student);
        String s = transcript.canChoose();
        if (s == null) {
            transcriptRepository.save(transcript);
            return "{\"msg\":\"操作成功，等待同步结果\"}";
        } else {
            return s;
        }
    }

    @Override
    public boolean unChooseOneSection(Integer id) {
        transcriptRepository.deleteById(id);
        return true;
    }
}
