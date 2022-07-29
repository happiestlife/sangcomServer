package capstone.sangcom.repository;

import capstone.sangcom.repository.dao.auth.TokenDAO;
import capstone.sangcom.repository.token.MySqlTokenRepository;
import capstone.sangcom.repository.user.MySqlUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static capstone.sangcom.testCase.token.TokenTestCase.*;
import static capstone.sangcom.testCase.user.UserTestCase.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MySqlTokenRepositoryTest {

    @Autowired
    private MySqlTokenRepository repository;

    @Autowired
    private MySqlUserRepository userRepository;

    @BeforeEach
    public void given() {
        userRepository.insert(USER1);
        userRepository.insert(USER2);
        userRepository.insert(USER3);

        repository.insert(TOKEN1);
        repository.insert(TOKEN2);
    }

    @AfterEach
    public void reset() {
        repository.removeAll();
        userRepository.removeAll();
    }

    @Test
    public void 토큰저장_성공() {
        repository.insert(TOKEN3);

        assertThat(repository.findAll().size()).isEqualTo(3);
        compareToken(TOKEN3, repository.findByToken(TOKEN3.getRefreshToken()));
    }

    @Test
    public void 토큰저장_실패_이미존재하는아아디() {
        assertThat(repository.insert(TOKEN2)).isNull();
    }

    @Test
    public void 토큰으로아이디찾기_성공() {
        TokenDAO tokenInfo = repository.findByToken(TOKEN1.getRefreshToken());

        assertThat(tokenInfo.getId()).isEqualTo(TOKEN1.getId());
        assertThat(tokenInfo.getRefreshToken()).isEqualTo(TOKEN1.getRefreshToken());
    }

    @Test
    public void 토큰으로아이디찾기_실패_존재하지않는토큰() {
        assertThat(repository.findByToken("wrongToken")).isEqualTo(null);
    }

    @Test
    public void 토큰업데이트_성공() {
        TokenDAO updateTokenData = new TokenDAO(TOKEN1.getId(), "testToken4");

        repository.update(updateTokenData);

        assertThat(repository.findByToken(updateTokenData.getRefreshToken()).getRefreshToken()).isEqualTo(updateTokenData.getRefreshToken());
        assertThat(repository.findByToken(updateTokenData.getRefreshToken()).getId()).isEqualTo(updateTokenData.getId());
    }

    @Test
    public void 토큰업데이트_실패_존재하지않는아이디() {
        TokenDAO wrongToken = new TokenDAO("wrongId", "wrongId");

        assertThat(repository.update(wrongToken)).isNull();
    }

    @Test
    public void 토큰삭제_성공() {
        repository.delete(TOKEN1.getRefreshToken());

        assertThat(repository.findAll().size()).isEqualTo(1);
        assertThat(repository.findByToken(TOKEN1.getRefreshToken())).isEqualTo(null);
    }

    @Test
    public void 토큰삭제_실패_존재하지않는아이디() {
        repository.delete("wrongRefreshToken");
    }

    private void compareToken(TokenDAO actual, TokenDAO expected) {
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getRefreshToken()).isEqualTo(expected.getRefreshToken());
    }

}
