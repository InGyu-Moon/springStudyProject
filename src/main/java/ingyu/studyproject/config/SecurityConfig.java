package ingyu.studyproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /**
         * 동작 순서는 상단 부터 적용된다. (순서 유의)
         * 만약 아래와 같은 코드가 있을경우
         * .requestMatchers("/").permitAll()
         * .requestMatchers("/").denyAll()
         * 첫줄에서 이미 permitAll()을 했으므로 deyALL()은 무시된다.
         */
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/login","/loginSuccess","/join","/joinSuccess").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated() // 나머지 경로에 대해서 로그인한 사용자는 접근 허용
//                        .anyRequest().denyAll() // 모두 접근 불가
                );

        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginSuccess")
                        .permitAll()
                );

        /**
         * 일단 csrf 토큰 비활성화
         */
        http
                .csrf((auth) -> auth
                        .disable()
                );

        return http.build();
    }

}
