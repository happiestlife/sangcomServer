package capstone.sangcom.repository;

import capstone.sangcom.entity.dao.auth.AuthStudentDAO;
import capstone.sangcom.repository.auth.MySqlAuthStudentRepository;
import capstone.sangcom.repository.dao.auth.AuthStudentDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static capstone.sangcom.testCase.user.auth.AuthTestCase.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MySqlAuthRepositoryTest {

    @Autowired
    private MySqlAuthStudentRepository repository;

    @BeforeEach
    public void given() {
        repository.removeAll();
        repository.insert(AUTH_STUDENT_DAO_1);
    }

    @Test
    public void 학번저장_성공(){
        assertThat(repository.insert(AUTH_STUDENT_DAO_2)).isNotNull();

        assertThat(repository.findAll().size()).isSameAs(2);

        AuthStudentDAO result = repository.find(AUTH_STUDENT_DAO_2);
        compareAuthStudent(AUTH_STUDENT_DAO_2, result);
    }

    @Test
    public void 학번저장_실패_아이디중복(){
        repository.insert(AUTH_STUDENT_DAO_1);
        assertThat(repository.insert(AUTH_STUDENT_DAO_1)).isNull();
    }

    @Test
    public void 학번조회_성공() {
        AuthStudentDAO result = repository.find(AUTH_STUDENT_DAO_1);

        compareAuthStudent(AUTH_STUDENT_DAO_1, result);
    }

    @Test
    public void 학번조회_실패_존재하지않는아이디() {
        assertThat(repository.find(new AuthStudentDAO("wrongId", AUTH_STUDENT_DAO_1.getStudent_id()))).isNull();
    }

    @Test
    public void 학번전체조회_성공() {
        List<AuthStudentDAO> auths = repository.findAll();
        assertThat(auths.size()).isSameAs(1);

        compareAuthStudent(AUTH_STUDENT_DAO_1, auths.get(0));
    }

    @Test
    public void 학번삭제_성공() {
        assertThat(repository.delete(AUTH_STUDENT_DAO_1)).isTrue();

        assertThat(repository.findAll().size()).isSameAs(0);
    }

    @Test
    public void 학번삭제_실패_존재하지않는아이디() {
        assertThat(repository.delete(new AuthStudentDAO("wrongId", AUTH_STUDENT_DAO_1.getStudent_id()))).isFalse();

        assertThat(repository.findAll().size()).isSameAs(1);
    }

    private void compareAuthStudent(AuthStudentDAO standard, AuthStudentDAO compare) {

        assertThat(compare.getName()).isEqualTo(standard.getName());
        assertThat(compare.getStudent_id()).isEqualTo(standard.getStudent_id());

    }
}