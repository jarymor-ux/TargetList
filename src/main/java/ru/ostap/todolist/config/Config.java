package ru.ostap.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ostap.todolist.service.UserDetailsService;

@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public Config(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/auth/registration", "/auth/login").anonymous()
                .antMatchers("/api/user/**", "/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

    }

}
