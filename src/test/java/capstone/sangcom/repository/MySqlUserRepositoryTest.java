package capstone.sangcom.repository;

import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.user.MySqlUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static capstone.sangcom.testCase.user.UserTestCase.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MySqlUserRepositoryTest {

    private final static UpdateUserInfoDTO UPDATE_TEST_DATA[] = {
            new UpdateUserInfoDTO("test", 10, 10, 10, "2022-05-01", 1999, "testEmail"),
            new UpdateUserInfoDTO("OVER_PHONE_SIZE_OVER_PHONE_SIZE_", 9, 9, 9, "2022-05-02", 1990, "OVER_SIZE_PHONE"),
    };

    private final static String WRONG_ID = "WRONG_ID";

    private final static String NEW_PASSWORD = "NEW_PASSWORD";

    @Autowired
    private MySqlUserRepository repository;

//    @BeforeAll
//    public static void setting() {
//        repository = new MySqlUserRepository(TestDataSourceManager.getDataSource());
//    }

    @BeforeEach
    public void given() {
        repository.insert(USER1);
        repository.insert(USER2);
        repository.insert(USER3);
    }

    @AfterEach
    public void reset(){
        repository.removeAll();
    }

    @Test
    public void 회원가입_성공() {
        repository.insert(USER4);

        assertThat(repository.findAll().size()).isEqualTo(4);
    }

    @Test
    public void 회원가입_실패_동일아이디존재() {
        assertThat(repository.insert(USER3)).isNull();
    }

    @Test
    public void 아이디로회원조회_성공() {
        User user = repository.findById(USER1.getId());

        compareUser(USER1, user);
    }

    @Test
    public void 아이디로회원조회_실패_존재하지않는아이디() {
        assertThat(repository.findById(WRONG_ID)).isEqualTo(null);
    }

    @Test
    public void 전체회원조회_성공() {
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(3);

        compareUser(USER1, users.get(0));
        compareUser(USER2, users.get(1));
        compareUser(USER3 ,users.get(2));
    }

    @Test
    public void 개인세부정보수정_성공() {
        repository.update(USER1.getId(), UPDATE_TEST_DATA[0]);

        compareDetailUser(UPDATE_TEST_DATA[0], repository.findById(USER1.getId()));
    }

    @Test
    // 아이디 존재 x시 어떤 익셉션 발생할지 아직 모름
    public void 개인세부정보수정_실패_존재하지않는아이디() {
        repository.update(WRONG_ID, UPDATE_TEST_DATA[0]);

    }

    @Test
    // 핸드폰 크기(VARCHAR(20))보다 큰 데이터 입력시 어떤 익셉션 발생할지 아직 모름
    public void 개인세부정보수정_실패_요구사항에맞지않는값입력() {
        assertThat(repository.update(USER1.getId(), UPDATE_TEST_DATA[1])).isFalse();
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
        repository.update(WRONG_ID, NEW_PASSWORD);
    }


    @Test
    public void 회원탈퇴_성공() {
        repository.delete(USER1.getId());

        assertThat(repository.findAll().size()).isEqualTo(2);
        assertThat(repository.findById(USER1.getId())).isEqualTo(null);
    }

    @Test
    public void 회원탈퇴_실패_존재하지않는아이디() {
        assertThat(repository.delete(WRONG_ID)).isFalse();

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