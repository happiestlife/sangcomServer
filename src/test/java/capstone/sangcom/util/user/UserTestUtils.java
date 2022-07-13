package capstone.sangcom.util.user;

import capstone.sangcom.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestUtils {

    private final static PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    private UserTestUtils() {}

    public static UpdateUserInfoDTO makeEditCase(UpdateUserInfoDTO standard, int part, String change){
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

    public static void compareUser(User standard, User compare) {
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

    public static User copyUser(User user){
        return new User(user.getId(), user.getPassword(), user.getName(), user.getPhone(),
                user.getSchoolgrade(), user.getSchoolclass(), user.getSchoolnumber(),
                user.getRole(), user.getYear(), user.getBirth(), user.getEmail());
    }
}
