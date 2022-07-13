package capstone.sangcom.testCase.user.auth;

import capstone.sangcom.repository.dao.auth.AuthStudentDAO;

public class AuthTestCase {

    public final static AuthStudentDAO AUTH_STUDENT_DAO_1 = new AuthStudentDAO("test1", "studentId1");

    public final static AuthStudentDAO AUTH_STUDENT_DAO_2 = new AuthStudentDAO("test2", "studentId2");

    public final static AuthStudentDAO AUTH_STUDENT_DAO_3 = new AuthStudentDAO("test3", "studentId3");

}
