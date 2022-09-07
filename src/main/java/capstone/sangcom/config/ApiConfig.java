package capstone.sangcom.config;

import capstone.sangcom.config.interceptor.LoginApiInterceptor;
import capstone.sangcom.config.interceptor.MasterAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiConfig implements WebMvcConfigurer {

    private static String EXCLUDE_API_PATHS[] = {

            // school 관련
            "/api/school/cafeteria/**", "/api/school/schedule",

            // reply 관련
            "/api/reply/goodcount/**", "/api/reply/replycount/**",

            // notice 관련
            "/api/notice",

            // board 관련
            "/api/board/goodcount/**", "/api/board/scrapcount/**", "/api/board/search",

            // user 관련


            // login 관련
            "/api/user/login", "/api/user/register", "/api/user/confirm/name",
            "/api/user/auth/student/check", "/api/user/password/find",

            // auth 관련
            "/api/auth/refresh", "/api/auth/valid",


    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtApiInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns(EXCLUDE_API_PATHS)
                .order(1);
    }

    @Bean
    public LoginApiInterceptor jwtApiInterceptor() {
        return new LoginApiInterceptor();
    }

}
