package capstone.sangcom.config;

import capstone.sangcom.config.interceptor.LoginWebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String[] EXCLUDE_PATHS = {
            // 로그인 관련
            "/login", "/register"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginWebInterceptor())
                .addPathPatterns("/")
                .excludePathPatterns(EXCLUDE_PATHS)
                .order(1);
    }

    @Bean
    public LoginWebInterceptor loginWebInterceptor() {
        return new LoginWebInterceptor();
    }
}
