package capstone.sangcom.service.user;

import capstone.sangcom.dto.login.MailDTO;

public interface EmailService {
    public void sendFeedback(MailDTO mailDTO);
}
