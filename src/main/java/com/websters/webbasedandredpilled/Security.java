package com.websters.webbasedandredpilled;

import com.websters.webbasedandredpilled.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepo userRepo;

    static InMemoryUserDetailsManager memAuth = new InMemoryUserDetailsManager();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        System.out.println("configuring users");


        UserDetails newAdmin = User.withUsername("admin")
                .password(passEncode().encode("passWord"))
                .roles("ADMIN").build();
        memAuth.createUser(newAdmin);

//        ArrayList<UsersToAdd> userList = (ArrayList<UsersToAdd>) userRepo.findAll();
//        for(UsersToAdd user : userList){
//            UserDetails newUserAdd = User.withUsername(user.getUsername())
//                    .password(passEncode().encode(user.getPassword()))
//                    .roles(user.getSecurityRoles()).build();
//            memAuth.createUser(newUserAdd);
//        }

        auth.userDetailsService(getUserDetailsService());

    }
    // ///////////////////////////////////////////
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("configuring user perms");

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

    @Bean
    public InMemoryUserDetailsManager getInMemoryUserDetailsManager(){
        System.out.println("*** Enter getInMemoryUserDetailsManager(");
        return memAuth;
    }

    @Bean
    public UserDetailsService getUserDetailsService(){

        return (name) -> userRepo.findFirstByUsername(name);
    }
}
