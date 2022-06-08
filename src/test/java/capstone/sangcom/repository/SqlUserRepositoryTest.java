package capstone.sangcom.repository;

import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.entity.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class SqlUserRepositoryTest {

    private final static User USER1 = new User("testId1", "testPw1", "test1", "11112345678",
            1, 1, 1, UserRole.valueOf("ADMIN"),
            2020, new Date(), "test1@naver.com");

    private final static User USER2 = new User("testId2", "testPw2", "test2", "22212345678",
            2, 2, 2, UserRole.valueOf("TEACHER"),
            2020, new Date(System.currentTimeMillis()+(1000*60*60*24)), "test2@naver.com");

    private final static User USER3 = new User("testId3", "testPw3", "test3", "33312345678",
            3, 3, 3, UserRole.valueOf("STUDENT"),
            2020, new Date(System.currentTimeMillis()+(1000*60*60*24)*2), "test3@naver.com");

    private final static User USER4 = new User("testId4", "testPw4", "test4", "44412345678",
            4, 4, 4, UserRole.valueOf("STUDENT"),
            2020, new Date(System.currentTimeMillis()+(1000*60*60*24)*3), "test4@naver.com");

    private final static UpdateUserInfoDTO UPDATE_TEST_DATA[] = {
            new UpdateUserInfoDTO("test", 10, 10, 10, new Date(System.currentTimeMillis() * 2), 1999, "testEmail"),
            new UpdateUserInfoDTO("OVER_PHONE_SIZE_OVER_PHONE_SIZE_", 9, 9, 9, new Date(System.currentTimeMillis() * 3), 1990, "OVER_SIZE_PHONE"),
    };

    private final static String NEW_PASSWORD = "NEW_PASSWORD";

    @Autowired
    private SqlUserRepository repository;

    @BeforeEach
    public void given() {
        repository.create(USER1);
        repository.create(USER2);
        repository.create(USER3);
    }

    @AfterEach
    public void reset(){
        repository.removeAll();
    }

    @Test
    public void 회원가입_성공() {
        repository.create(USER4);

        assertThat(repository.findAll().size()).isEqualTo(4);
    }

    @Test
    public void 회원가입_실패_동일아이디존재() {
        org.junit.jupiter.api.Assertions.assertThrows(DuplicateKeyException.class,
                ()-> repository.create(USER3));
    }

    @Test
    public void 아이디로회워조회_성공() {
        User user = repository.findById(USER1.getId());

        compareUser(USER1, user);
    }

    @Test
    public void 아이디로회워조회_실패_존재하지않는아이디() {
        assertThat(repository.findById("wrongId")).isEqualTo(null);
    }


    @Test
    public void 개인세부정보수정_성공() {
        repository.update(USER1.getId(), UPDATE_TEST_DATA[0]);

        compareDetailUser(UPDATE_TEST_DATA[0], repository.findById(USER1.getId()));
    }

    @Test
    // 아이디 존재 x시 어떤 익셉션 발생할지 아직 모름
    public void 개인세부정보수정_실패_존재하지않는아이디() {
        repository.update("wrongId", UPDATE_TEST_DATA[0]);

    }

    @Test
    // 핸드폰 크기(VARCHAR(20))보다 큰 데이터 입력시 어떤 익셉션 발생할지 아직 모름
    public void 개인세부정보수정_실패_요구사항에맞지않는값입력() {
        repository.update(USER1.getId(), UPDATE_TEST_DATA[1]);
    }

    @Test
    @Disabled
    // 프로필사진 관련 테스트
    public void update() {

    }

    @Test
    public void 비밀번호변경_성공() {

        repository.update(USER1.getId(), NEW_PASSWORD);

        assertThat(repository.findById(USER1.getId()).getPassword()).isEqualTo(NEW_PASSWORD);
    }

    @Test
    public void 비밀번호변경_실패_존재하지않는아이디() {
        repository.update("wrongId", NEW_PASSWORD);
    }


    @Test
    public void 회원탈퇴_성공() {
        repository.delete(USER1.getId());

        assertThat(repository.findAll().size()).isEqualTo(2);
        Assertions.assertThrows(Exception.class, () -> repository.findById(USER1.getId()));
    }

    @Test
    public void 회원탈퇴_실패_존재하지않는아이디() {
        Assertions.assertThrows(Exception.class, () -> repository.findById("wrongId"));

        assertThat(repository.findAll().size()).isEqualTo(3);
    }

    private void compareUser(User standard, User compare) {
        assertThat(standard.getId()).isEqualTo(compare.getId());
        assertThat(standard.getPassword()).isEqualTo(compare.getPassword());
        assertThat(standard.getName()).isEqualTo(compare.getName());
        assertThat(standard.getPhone()).isEqualTo(compare.getPhone());
        assertThat(standard.getSchoolgrade()).isEqualTo(compare.getSchoolgrade());
        assertThat(standard.getSchoolclass()).isEqualTo(compare.getSchoolclass());
        assertThat(standard.getSchoolnumber()).isEqualTo(compare.getSchoolnumber());
        assertThat(standard.getRole()).isEqualTo(compare.getRole());
        assertThat(standard.getYear()).isEqualTo(compare.getYear());
        assertThat(standard.getBirth()).isEqualTo(compare.getBirth());
        assertThat(standard.getEmail()).isEqualTo(compare.getEmail());
    }

    private void compareDetailUser(UpdateUserInfoDTO standard, User compare) {
        assertThat(standard.getPhone()).isEqualTo(compare.getPhone());
        assertThat(standard.getSchoolgrade()).isEqualTo(compare.getSchoolgrade());
        assertThat(standard.getSchoolclass()).isEqualTo(compare.getSchoolclass());
        assertThat(standard.getSchoolnumber()).isEqualTo(compare.getSchoolnumber());
        assertThat(standard.getYear()).isEqualTo(compare.getYear());
        assertThat(standard.getBirth()).isEqualTo(compare.getBirth());
        assertThat(standard.getEmail()).isEqualTo(compare.getEmail());
    }
}