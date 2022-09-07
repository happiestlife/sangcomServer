package capstone.sangcom.service.report.reply;

import capstone.sangcom.entity.dto.reportSection.*;
import capstone.sangcom.entity.dao.replyReport.ReplyReportDAO;
import capstone.sangcom.entity.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;
import capstone.sangcom.repository.report.replyReport.ReplyReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyReportServiceImpl implements ReplyReportService{

    private final ReplyReportRepository replyReportRepository;
    @Override
    @Transactional
    public List<ReplyReportDTO> getMyReplyReport(String userId) {
        return replyReportRepository.getMyReplyReport(userId);
    }

    @Override
    @Transactional
    public boolean reportReply(String userId, PostReplyReportDTO postReplyReportDTO) {
        if(replyReportRepository.replyReport(new ReplyReportDAO(1, postReplyReportDTO.getBoard_id(), 1,
                userId, postReplyReportDTO.getRecv_id(), postReplyReportDTO.getBody())) == -1)
            return false;
        else
            return true;
    }

    @Override
    @Transactional
    public List<ReplyReportCountDTO> countReplyReportById() {
        return replyReportRepository.countReplyReportById();
    }

    @Override
    @Transactional
    public List<ReplyReportDTO> getReplyReportById(String recvId) {
        return replyReportRepository.getReplyReportById(recvId);
    }

    @Override
    @Transactional
    public List<ReplyReportPageDTO> getReplyReport(int page) {
        return replyReportRepository.getReplyReport(page);
    }
}
