package com.pj_module5.security.config;

import com.pj_module5.security.jwt.AccessDenied;
import com.pj_module5.security.jwt.JWTEntryPoint;
import com.pj_module5.security.jwt.JWTProvider;
import com.pj_module5.security.jwt.JWTTokenFilter;
import com.pj_module5.security.principle.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    private JWTEntryPoint jwtEntryPoint;
    @Autowired
    private JWTTokenFilter jwtTokenFilter;
    @Autowired
    private AccessDenied accessDenied;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).
                authenticationProvider(authenticationProvider()).
                authorizeRequests(
                        (auth)->auth.requestMatchers("/auth/**","/products/**","/*", "/product/**").permitAll().
                                requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                ).exceptionHandling(
                        (auth)->auth.authenticationEntryPoint(jwtEntryPoint).
                        accessDeniedHandler(accessDenied)
                        ).
                sessionManagement((auth)->auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}