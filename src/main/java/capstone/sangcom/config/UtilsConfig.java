package capstone.sangcom.config;

import capstone.sangcom.util.image.ImageUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public ImageUtils imageUtils() {
        return new ImageUtils();
    }

    @Bean
    public capstone.sangcom.util.user.ImageUtils imageUtil() {
        return new capstone.sangcom.util.user.ImageUtils();
    }
}
