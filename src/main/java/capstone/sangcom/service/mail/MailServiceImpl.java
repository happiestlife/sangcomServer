package capstone.sangcom.service.mail;

import capstone.sangcom.dto.mailSection.MailInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private static final String FROM_ADDRESS = "sangcom@gmail.com";

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(MailInfoDTO mailInfoDTO) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(mailInfoDTO.getToAddress());
        mail.setFrom(FROM_ADDRESS);
        mail.setSubject(mailInfoDTO.getSubject());
        mail.setText(mailInfoDTO.getMessage());

        javaMailSender.send(mail);
    }
}
