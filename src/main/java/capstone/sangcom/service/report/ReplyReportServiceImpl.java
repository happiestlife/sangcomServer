package capstone.sangcom.service.report;

import capstone.sangcom.entity.dto.reportSection.PostReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReadReplyReportDTO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportDTO;
import capstone.sangcom.entity.dao.replyReport.ReplyReportDAO;
import capstone.sangcom.entity.dto.reportSection.ReplyReportPageDTO;
import capstone.sangcom.repository.report.ReplyReportRepository;
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

    @Override
    @Transactional
    public List<ReplyReportPageDTO> getReplyReport() {
        return replyReportRepository.getReplyReport();
    }
}
