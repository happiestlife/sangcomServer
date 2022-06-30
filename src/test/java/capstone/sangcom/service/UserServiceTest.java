package capstone.sangcom.service;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.dto.login.FindPasswordDTO;
import capstone.sangcom.dto.login.LoginDTO;
import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.token.MemoryTokenRepository;
import capstone.sangcom.repository.user.MemoryUserRepository;
import capstone.sangcom.service.token.TokenServiceImpl;
import capstone.sangcom.service.user.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static capstone.sangcom.testCase.user.UserTestCase.USER1;
import static capstone.sangcom.testCase.user.UserTestCase.USER2;
import static org.assertj.core.api.Assertions.*;


public class UserServiceTest {

    private final UpdateUserInfoDTO editCase = new UpdateUserInfoDTO(
            "testPhone", 1, 1, 1,
            "2022-6-29", 2022, "testEmail");

    private MemoryUserRepository memoryUserRepository = new MemoryUserRepository();

    private PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    private UserServiceImpl userService = new UserServiceImpl(
            memoryUserRepository,
            passwordEncoder,
            new TokenServiceImpl(
                    memoryUserRepository,
                    new MemoryTokenRepository()
            )
    );

    @BeforeEach
    public void given() {
        memoryUserRepository.removeAll();

        User user = new User(USER1.getId(), passwordEncoder.encode(USER1.getPassword()), USER1.getName(), USER1.getPhone(),
                USER1.getSchoolgrade(),USER1.getSchoolclass(), USER1.getSchoolnumber(),
                USER1.getRole(), USER1.getYear(), USER1.getBirth(), USER1.getEmail());
        memoryUserRepository.insert(user);
    }

    @Test
    public void 로그인_성공(){
        LoginResponse result = userService.login(new LoginDTO(USER1.getId(), USER1.getPassword()));

        assertThat(result).isNotNull();
        assertThat(result.getRole()).isEqualTo(USER1.getRole());
    }

    @Test
    public void 로그인_실패_존재하지않는아이디(){
        assertThat(userService.login(new LoginDTO("wrongId", USER1.getPassword()))).isNull();
    }

    @Test
    public void 로그인_실패_일치하지않는비밀번호(){
        assertThat(userService.login(new LoginDTO(USER1.getId(), "wrongPassword"))).isNull();
    }

    @Test
    public void 로그인_실패_존재하지않는아이디_일치하지않는비밀번호(){
        assertThat(userService.login(new LoginDTO("wrongId", "wrongPassword"))).isNull();
    }


    @Test
    @Disabled
    public void 비밀번호찾기_성공(){
        String studentId;
        if(USER1.getSchoolclass() / 10 == 0)
            studentId = USER1.getSchoolgrade() + (0 + String.valueOf(USER1.getSchoolclass())) + USER1.getSchoolnumber();
        else
            studentId = USER1.getSchoolgrade() + (String.valueOf(USER1.getSchoolclass())) + USER1.getSchoolnumber();

        assertThat(userService.findPassword(new FindPasswordDTO(USER1.getId(), USER1.getName(), studentId))).isEqualTo(USER1.getPassword());
    }

    @Test
    @Disabled
    public void 비밀번호찾기_실패_잘못된아이디_이름_학번(){
        String studentId;
        if(USER1.getSchoolclass() / 10 == 0)
            studentId = USER1.getSchoolgrade() + (0 + String.valueOf(USER1.getSchoolclass())) + USER1.getSchoolnumber();
        else
            studentId = USER1.getSchoolgrade() + (String.valueOf(USER1.getSchoolclass())) + USER1.getSchoolnumber();

        assertThat(userService.findPassword(new FindPasswordDTO(USER1.getId(), USER1.getName(), studentId))).isEqualTo(USER1.getPassword());
    }

    @Test
    public void 아이디로회원찾기_성공(){
        User user = userService.findById(USER1.getId());
        compareUser(USER1, user);
    }

    @Test
    public void 아이디로회원찾기_실패_존재하지않는아이디(){
        Assertions.assertThat(userService.findById("wrongId")).isNull();
    }

    @Test
    public void 회원가입_성공() {
        User registerUser = copyUser(USER2);
        userService.register(registerUser);

        User user = memoryUserRepository.findById(USER2.getId());
        compareUser(USER2, user);
    }

    @Test
    public void 회원가입_실패_존재하는아이디() {
        assertThat(userService.register(USER1)).isNull();
    }

    @Test
    @Disabled
    public void 프로필변경_성공() {

    }

    @Test
    public void 비밀번호변경_성공() {
        userService.editPassword(USER1.getId(), "edit");

        User user = memoryUserRepository.findById(USER1.getId());
        assertThat(passwordEncoder.matches("edit", user.getPassword())).isTrue();
    }

    @Test
    public void 비밀번호변경_실패_존재하지않는아이디() {
        assertThat(userService.editPassword("wrongId", "edit")).isFalse();
    }

    @Test
    public void 회원정보수정_성공() {
        assertThat(userService.editUserInfo(USER1.getId(), editCase)).isTrue();
    }

    @Test
    public void 회원정보수정_실패_존재하지않는아이디() {
        assertThat(userService.editUserInfo("wrongId", editCase)).isFalse();
    }

    @Test
    public void 회원정보수정_실패_길이초과() {
        UpdateUserInfoDTO overLength_phone = makeEditCase(editCase, 1, "012345678901234567891");

        String email = "0123456789";
        for(int i = 0; i < 11; i++)
            email += email;
        UpdateUserInfoDTO overLength_email = makeEditCase(editCase, 7, email);

        assertThat(userService.editUserInfo("wrongId", overLength_phone)).isFalse();
        assertThat(userService.editUserInfo("wrongId", overLength_email)).isFalse();
    }

//    public boolean leave(String id);

    private UpdateUserInfoDTO makeEditCase(UpdateUserInfoDTO standard, int part, String change){
        UpdateUserInfoDTO newCase = null;

        switch (part) {
            case 1:
                newCase = new UpdateUserInfoDTO(change, standard.getSchoolgrade(), standard.getSchoolclass(),
                    standard.getSchoolnumber(), standard.getBirth(),
                    standard.getYear(), standard.getEmail());
                break;
            case 7:
                newCase = new UpdateUserInfoDTO(standard.getPhone(), standard.getSchoolgrade(), standard.getSchoolclass(),
                        standard.getSchoolnumber(), standard.getBirth(),
                        standard.getYear(), change);
                break;
        }
        return newCase;
    }

    private void compareUser(User standard, User compare) {
        assertThat(standard.getId()).isEqualTo(compare.getId());
        assertThat(passwordEncoder.matches(standard.getPassword(), compare.getPassword())).isTrue();
        assertThat(standard.getName()).isEqualTo(compare.getName());
        assertThat(standard.getPhone()).isEqualTo(compare.getPhone());
        assertThat(standard.getSchoolgrade()).isSameAs(compare.getSchoolgrade());
        assertThat(standard.getSchoolclass()).isSameAs(compare.getSchoolclass());
        assertThat(standard.getSchoolnumber()).isSameAs(compare.getSchoolnumber());
        assertThat(standard.getRole()).isEqualTo(compare.getRole());
        assertThat(standard.getYear()).isSameAs(compare.getYear());
        assertThat(standard.getBirth()).isEqualTo(compare.getBirth());
        assertThat(standard.getEmail()).isEqualTo(compare.getEmail());
    }

    private User copyUser(User user){
        return new User(user.getId(), user.getPassword(), user.getName(), user.getPhone(),
                user.getSchoolgrade(), user.getSchoolclass(), user.getSchoolnumber(),
                user.getRole(), user.getYear(), user.getBirth(), user.getEmail());
    }
}
