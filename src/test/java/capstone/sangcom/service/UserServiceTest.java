package capstone.sangcom.service;

import capstone.sangcom.controller.api.response.login.LoginResponse;
import capstone.sangcom.dto.loginSection.find.FindPasswordDTO;
import capstone.sangcom.dto.loginSection.login.LoginDTO;
import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.token.MemoryTokenRepository;
import capstone.sangcom.repository.user.MemoryUserRepository;
import capstone.sangcom.service.token.TokenServiceImpl;
import capstone.sangcom.service.login.LoginServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static capstone.sangcom.testCase.user.UserTestCase.USER1;
import static capstone.sangcom.testCase.user.UserTestCase.USER2;
import static capstone.sangcom.util.user.UserTestUtils.compareUser;
import static capstone.sangcom.util.user.UserTestUtils.copyUser;
import static org.assertj.core.api.Assertions.*;


public class UserServiceTest {

    private MemoryUserRepository memoryUserRepository = new MemoryUserRepository();

    private PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    private LoginServiceImpl userService = new LoginServiceImpl(
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
}
