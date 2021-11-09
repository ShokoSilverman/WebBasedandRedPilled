package com.websters.webbasedandredpilled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        System.out.println("configure - A");


        auth.inMemoryAuthentication().withUser("admin").password(passEncode()
                .encode("asd")).roles("ADMIN");

    }
    // ///////////////////////////////////////////
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("configure - B");

        http.authorizeRequests()
                .antMatchers("/anyone/**").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .httpBasic();
        http.cors();

    }
    // ///////////////////////////////////////////
    @Bean
    PasswordEncoder passEncode() {
        return new BCryptPasswordEncoder();
    }
}
