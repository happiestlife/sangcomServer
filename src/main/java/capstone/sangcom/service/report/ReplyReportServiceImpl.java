package capstone.sangcom.service.report;

import capstone.sangcom.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.dto.reportSection.ReadReplyReportDTO;
import capstone.sangcom.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.repository.dao.replyReport.ReplyReportDAO;
import capstone.sangcom.repository.report.ReplyReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyReportServiceImpl implements ReplyReportService{

    private final ReplyReportRepository replyReportRepository;
    @Override
    @Transactional
    public ReadReplyReportDTO getMyReplyReport(String userId) {
        ReplyReportDTO replyReportDTO = replyReportRepository.getMyReplyReport(userId);

        return new ReadReplyReportDTO(replyReportDTO);
    }

    @Override
    @Transactional
    public boolean reportReply(PostReplyReportDTO postReplyReportDTO) {
        replyReportRepository.replyReport(new ReplyReportDAO(-1, postReplyReportDTO.getBoard_id(), -1, "dsad", postReplyReportDTO.getRecv_id(), postReplyReportDTO.getBody()));
        return true;
    }
}
