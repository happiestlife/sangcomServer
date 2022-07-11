package capstone.sangcom.util.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ImageUtils {

    public static final int BOARD = 0;

    public static final int PROFILE = 1;

    @Value("${image.boardPath}")
    private static String BOARD_IMAGE_PATH;

    @Value("${image.profilePath}")
    private static String PROFILE_IMAGE_PATH;

    public static String makePath(int type, MultipartFile image) {
        return image.getOriginalFilename() + new Date().getTime() + ".jpg";
    }

    public static void store(MultipartFile image, File imageFile) throws IOException {
        image.transferTo(imageFile);
    }

}
