package capstone.sangcom.util.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

public class ImageUtils {

    public static final int BOARD = 0;

    public static final int PROFILE = 1;

    @Value("${image.boardPath}")
    private String BOARD_IMAGE_PATH;

    @Value("${image.profilePath}")
    private String PROFILE_IMAGE_PATH;

    public String makePath(int type, MultipartFile image) {
        if(type == BOARD)
            return BOARD_IMAGE_PATH + new Date().getTime() + image.getOriginalFilename();
        else
            return PROFILE_IMAGE_PATH + new Date().getTime() + image.getOriginalFilename();
    }

    public String getPath(String path) {
        StringTokenizer stk = new StringTokenizer(path, "\\");
        String str = "";
        while (stk.hasMoreTokens()) {
            str = stk.nextToken();
        }
        return str;
    }

    public void store(MultipartFile image, File imageFile) throws IOException {
        image.transferTo(imageFile);
    }

    public void delete(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

}
