package capstone.sangcom.testCase.user;

import capstone.sangcom.dto.login.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.entity.UserRole;

public class UserTestCase {

    public final static User USER1 = new User("test1", "testPw1", "test1", "11112345678",
            1, 1, 1, UserRole.valueOf("ADMIN"),
            2020, "2022-06-01", "test1@naver.com");

    public final static User USER2 = new User("test2", "testPw2", "test2", "22212345678",
            2, 2, 2, UserRole.valueOf("TEACHER"),
            2020, "2022-06-02", "test2@naver.com");

    public final static User USER3 = new User("test3", "testPw3", "test3", "33312345678",
            3, 3, 3, UserRole.valueOf("STUDENT"),
            2020, "2022-06-03", "test3@naver.com");

    public final static User USER4 = new User("test4", "testPw4", "test4", "44412345678",
            4, 4, 4, UserRole.valueOf("STUDENT"),
            2020, "2022-06-04", "test4@naver.com");

}
