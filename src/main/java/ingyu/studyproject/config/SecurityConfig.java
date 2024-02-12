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
                        .requestMatchers("/","/login","/logout","/loginSuccess","/join","/joinSuccess").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated() // 나머지 경로에 대해서 로그인한 사용자는 접근 허용
//                        .anyRequest().denyAll() // 모두 접근 불가
                );
        /**
         * 다중 로그인 설정
         * maximumSessions를 통해 로그인 가능한 세션의 수를 설정한다.
         * maxSessionsPreventsLogin으로 로그인 초과시 로그인 차단을 설정할 수 있다.
         * true이면 새로운 로그인을 차단한다.
         * false면 기존 로그인을 차단한다.
         */
        http
                .sessionManagement( (auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                );

        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginSuccess")
                        .permitAll()
                );

        http
                .logout((auth) -> auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        /**
         * 개발환경에서는 csrf 토큰 비활성화 해도 되지만
         * 배포를 할때는 csrf 공격을 방지하기 위해 활성화 해야한다.
         * 활성화를 하면 post 전송을 하는 form 태크 내부에
         * <input type="hidden" name="_csrf" th:value="${_csrf.token}"> 를 추가해줘야 한다.
         * default 값은 enable이다.
         */
//        http
//                .csrf((auth) -> auth
//                        .disable()
//                );

        return http.build();
    }

}
