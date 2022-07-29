package capstone.sangcom.service.mail;

import capstone.sangcom.dto.mailSection.MailInfoDTO;

public interface MailService {

    public static final String PASSWORD_SUBJECT = "[Sangcom] 임시 비밀번호 전송";

    public void sendMail(MailInfoDTO mailInfoDTO);
}
