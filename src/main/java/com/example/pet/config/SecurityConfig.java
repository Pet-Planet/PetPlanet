package com.example.pet.config;

import com.example.pet.config.jwt.CustomAuthenticationEntryPoint;
import com.example.pet.config.jwt.JwtAuthenticationFilter;
import com.example.pet.config.jwt.JwtProvider;
import com.example.pet.config.jwt.JwtRequestFilter;
import com.example.pet.repository.MemberRepository;
import com.example.pet.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    public static final String FRONT_URL = "http://localhost:8088";

    private final CorsFilter corsFilter;


    @Bean
    public BCryptPasswordEncoder encodePwd() {
    // 비밀번호 암호화를 위한 코드
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //로그인&로그아웃 처리를 위한 코드
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .addFilter(corsFilter);
        http.authorizeRequests()
                .antMatchers(FRONT_URL+"/**")
                .authenticated()
                .anyRequest().permitAll()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        http.addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);        // 세션을 사용하지 않는다고 설정한다
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //.addFilter(new JwtRequestFilter(authenticationManager(), memberRepository));

    }
}