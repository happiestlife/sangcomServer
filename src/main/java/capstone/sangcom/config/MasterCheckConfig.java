package capstone.sangcom.config;

import capstone.sangcom.config.interceptor.MasterAuthApiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MasterCheckConfig implements WebMvcConfigurer {
    @Bean
    public MasterAuthApiInterceptor masterAuthApiInterceptor() {
        return new MasterAuthApiInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(masterAuthApiInterceptor())
                .addPathPatterns("/api/user/auth/student", "/api/user/auth/student/all")
                .order(1);
    }
}
