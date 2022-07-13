package capstone.sangcom.config;

import capstone.sangcom.util.auth.JwtUtils;
import capstone.sangcom.util.board.ImageUtils;
import capstone.sangcom.util.login.TempPasswordUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public ImageUtils imageUtils() {
        return new ImageUtils();
    }
}
