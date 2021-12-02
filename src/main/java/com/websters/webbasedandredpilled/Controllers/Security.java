package com.websters.webbasedandredpilled.Controllers;

import com.websters.webbasedandredpilled.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
        auth.inMemoryAuthentication().withUser(newAdmin);
        //memAuth.createUser(newAdmin);
        System.out.println("Other user exists " + memAuth.userExists("admin"));

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
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .and().formLogin().loginPage("/registration.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/chatRoom.html", true)
                .and()
                .logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                //.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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
