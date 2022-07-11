package capstone.sangcom.util.login;

import java.util.Random;

/**
 * 비밀번호 찾기에서 5~15자리 임시 비밀번호를 생성해주는 Util
 */
public class TempPasswordUtils {
    private static final String UPPER_CASE_ALPHABET[] = {
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"
    };

    private static final String LOWER_CASE_ALPHABET[] = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z"
    };

    private static final String NUMERIC[] = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    private static final String SPECIAL_CHARACTER[] = {
            "*", "_", "!", "@"
    };

    public static String makeTemporaryPassword() {
        Random random = new Random(System.currentTimeMillis());

        int length = random.nextInt(10) + 5;

        String temporaryPassword = "";
        for (int i = 0; i < length; i++) {
            int whichPart = random.nextInt(3);
            temporaryPassword += choice(random, whichPart);
        }

        return temporaryPassword;
    }

    private static String choice(Random random, int part) {
        String arr[] = null;
        switch (part){
            case 0:
                arr = UPPER_CASE_ALPHABET;
                break;
            case 1:
                arr = LOWER_CASE_ALPHABET;
                break;
            case 2:
                arr = NUMERIC;
                break;
            case 3:
                arr = SPECIAL_CHARACTER;
                break;
        }
        System.out.println("part " + part);
        System.out.println(random.nextInt(arr.length));

        return arr[random.nextInt(arr.length)];
    }
}
