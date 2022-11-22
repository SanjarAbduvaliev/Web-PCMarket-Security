package com.example.webpcmarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("super_admin")
                .password(encoder().encode("super_admin_password"))
                .roles("SUPER_ADMIN")

                .and()

                .withUser("moderator")
                .password(encoder().encode("moderator_password"))
                .roles("MODERATOR")

                .and()
                .withUser("operator")
                .password(encoder().encode("operator"))
                .roles("OPERATOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         *TIZIMGA KIRISH HUQUQLARINI BOSHQARISH(AUTORIZATSIYADAN O'TKAZISH)
         */
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()

//                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole("SUPER_ADMIN","MODERATOR","OPERATOR")
//                .antMatchers(HttpMethod.POST,"/api/**").hasAnyRole("MODERATOR","SUPER_ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/**").hasAnyRole("SUPER_ADMIN","SUPER_ADMIN")
//                .antMatchers("/api/**").hasRole("SUPER_ADMIN")
                .and()
                .httpBasic();
    }
    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
