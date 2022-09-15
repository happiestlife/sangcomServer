//<<<<<<< Updated upstream
////package capstone.sangcom.service.user;
////
////import capstone.sangcom.dto.login.MailDTO;
////import capstone.sangcom.entity.User;
////import capstone.sangcom.repository.user.UserRepository;
////import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.mail.SimpleMailMessage;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////import lombok.AllArgsConstructor;
////
////@Service
////@AllArgsConstructor
////public class EmailServiceImpl implements EmailService{
////
////    @Autowired
////    private UserRepository userRepository;
////
////    private PasswordEncoder passwordEncoder;
////    private JavaMailSender emailSender;
////
//////    @Override
//////    @Bean
//////    public void sendFeedback(MailDTO mailDTO) {
//////        SimpleMailMessage message = new SimpleMailMessage();
//////        message.setFrom("coreminw@naver.com");
//////        message.setTo(mailDTO.getAddress());
//////        message.setSubject(mailDTO.getTitle());
//////        message.setText(mailDTO.getContent());
//////        emailSender.send(message);
//////    }
////
////    @Override
////    public MailDTO createMailAndChangePassword(User user) {
////        String str = getTempPassword();
////        MailDTO dto = new MailDTO();
////        dto.setAddress(user.getEmail());
////        dto.setTitle(user.getName()+"님의 임시비밀번호 안내 이메일 입니다.");
////        dto.setContent("안녕하세요. HOTTHINK 임시비밀번호 안내 관련 이메일 입니다." + "[" + user.getName() + "]" +"님의 임시 비밀번호는 "
////                + str + " 입니다.");
////        updatePassword(str,user);
////        return dto;
////    }
////
////    @Override
////    public void mailSend(MailDTO mailDTO) {
////        System.out.println("이멜 전송 완료!");
////        SimpleMailMessage message = new SimpleMailMessage();
////        message.setTo(mailDTO.getAddress());
////        message.setSubject(mailDTO.getTitle());
////        message.setText(mailDTO.getContent());
////
////        emailSender.send(message);
////    }
////
////    public void updatePassword(String str, User user){
////        String pw = passwordEncoder.encode(str);
////        String id = userRepository.findById(user.getEmail()).getId();
////        userRepository.update(id,pw);
////    }
////
////    public String getTempPassword(){
////        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
////                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
////
////        String str = "";
////
////        int idx = 0;
////        for (int i = 0; i < 10; i++) {
////            idx = (int) (charSet.length * Math.random());
////            str += charSet[idx];
////        }
////        return str;
////    }
////}
//=======
//package capstone.sangcom.service.user;
//
//import capstone.sangcom.dto.login.FindPasswordDTO;
//import capstone.sangcom.dto.login.MailDTO;
//import capstone.sangcom.entity.User;
//import capstone.sangcom.repository.user.UserRepository;
//import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import lombok.AllArgsConstructor;
//
//@Service
//@AllArgsConstructor
//public class EmailServiceImpl implements EmailService{
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private PasswordEncoder passwordEncoder;
//    private JavaMailSender emailSender;
//
//    @Override
//    public void sendFeedback(MailDTO mailDTO) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("coreminw@naver.com");
//        message.setTo(mailDTO.getAddress());
//        message.setSubject(mailDTO.getTitle());
//        message.setText(mailDTO.getContent());
//        emailSender.send(message);
//    }
//
//    @Override
//    public MailDTO createMailAndChangePassword(User user) {
//        String str = getTempPassword();
//        MailDTO dto = new MailDTO();
//        dto.setAddress(user.getEmail());
//        dto.setTitle(user.getName()+"님의 임시비밀번호 안내 이메일 입니다.");
//        dto.setContent("안녕하세요. HOTTHINK 임시비밀번호 안내 관련 이메일 입니다." + "[" + user.getName() + "]" +"님의 임시 비밀번호는 "
//                + str + " 입니다.");
//        updatePassword(str,user);
//        return dto;
//    }
//
//    @Override
//    public void mailSend(MailDTO mailDTO) {
//        System.out.println("이멜 전송 완료!");
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mailDTO.getAddress());
//        message.setSubject(mailDTO.getTitle());
//        message.setText(mailDTO.getContent());
//
//        emailSender.send(message);
//    }
//
//    public void updatePassword(String str, User user){
//        String pw = passwordEncoder.encode(str);
//        String id = userRepository.findById(user.getEmail()).getId();
//        userRepository.update(id,pw);
//    }
//
//    public String getTempPassword(){
//        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
//                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
//
//        String str = "";
//
//        int idx = 0;
//        for (int i = 0; i < 10; i++) {
//            idx = (int) (charSet.length * Math.random());
//            str += charSet[idx];
//        }
//        return str;
//    }
//}
//>>>>>>> Stashed changes
