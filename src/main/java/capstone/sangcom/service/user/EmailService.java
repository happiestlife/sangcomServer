package capstone.sangcom.service.user;

import capstone.sangcom.dto.login.MailDTO;
import capstone.sangcom.entity.User;

public interface EmailService {
    public void sendFeedback(MailDTO mailDTO);
    public MailDTO createMailAndChangePassword(User user);
    public void mailSend(MailDTO mailDTO);
}
