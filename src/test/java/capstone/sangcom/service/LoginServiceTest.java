//package capstone.sangcom.service;
//
//import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
//import capstone.sangcom.entity.User;
//import capstone.sangcom.repository.user.MemoryUserRepository;
//import capstone.sangcom.repository.user.UserRepository;
//import capstone.sangcom.service.user.UserService;
//import capstone.sangcom.service.user.UserServiceImpl;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static capstone.sangcom.testCase.user.UserTestCase.USER1;
//import static capstone.sangcom.util.user.UserTestUtils.compareUser;
//import static capstone.sangcom.util.user.UserTestUtils.makeEditCase;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class LoginServiceTest {
//
//    private final UpdateUserInfoDTO editCase = new UpdateUserInfoDTO(
//            "testPhone", 1, 1, 1,
//            "2022-6-29", 2022, "testEmail");
//
//    private MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
//
//    private PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
//
//    private final UserService userService = new UserServiceImpl(new MemoryUserRepository(), passwordEncoder);
//
//    @Test
//    public void 아이디로회원찾기_성공(){
//        User user = userService.findById(USER1.getId());
//        compareUser(USER1, user);
//    }
//
//    @Test
//    public void 아이디로회원찾기_실패_존재하지않는아이디(){
//        Assertions.assertThat(userService.findById("wrongId")).isNull();
//    }
//    @Test
//    public void 비밀번호변경_성공() {
//        userService.editPassword(USER1.getId(), "edit");
//
//        User user = memoryUserRepository.findById(USER1.getId());
//        assertThat(passwordEncoder.matches("edit", user.getPassword())).isTrue();
//    }
//
//    @Test
//    public void 비밀번호변경_실패_존재하지않는아이디() {
//        assertThat(userService.editPassword("wrongId", "edit")).isFalse();
//    }
//
//    @Test
//    public void 회원정보수정_성공() {
//        assertThat(userService.editUserInfo(USER1.getId(), editCase)).isTrue();
//    }
//
//    @Test
//    public void 회원정보수정_실패_존재하지않는아이디() {
//        assertThat(userService.editUserInfo("wrongId", editCase)).isFalse();
//    }
//
//    @Test
//    public void 회원정보수정_실패_길이초과() {
//        UpdateUserInfoDTO overLength_phone = makeEditCase(editCase, 1, "012345678901234567891");
//
//        String email = "0123456789";
//        for(int i = 0; i < 11; i++)
//            email += email;
//        UpdateUserInfoDTO overLength_email = makeEditCase(editCase, 7, email);
//
//        assertThat(userService.editUserInfo("wrongId", overLength_phone)).isFalse();
//        assertThat(userService.editUserInfo("wrongId", overLength_email)).isFalse();
//    }
//
//    //    public boolean leave(String id);
//}
