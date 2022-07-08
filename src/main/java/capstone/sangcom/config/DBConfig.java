package capstone.sangcom.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DBConfig {

    private final DataSource dataSource;

    @Bean
    public NamedParameterJdbcTemplate name(){
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
