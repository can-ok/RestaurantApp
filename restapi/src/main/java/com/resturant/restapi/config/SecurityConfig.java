package com.resturant.restapi.config;

import com.resturant.restapi.auth.UserDetailsServiceImpl;
import liquibase.pro.packaged.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests();
        http.authorizeRequests().antMatchers("/products/**").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/users/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/orders/**").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/category/**").permitAll();
        http.authorizeRequests().antMatchers("/info/**").permitAll();
        http.authorizeRequests().antMatchers("/waiters/**").permitAll();
        http.authorizeRequests().antMatchers("/media/**").permitAll();
        http.authorizeRequests().antMatchers("/roles/**").permitAll();
        http.authorizeRequests().antMatchers("/table/**").access("hasAnyRole('ADMIN','USER')");

        http.httpBasic();
        http.cors();
        //http.authorizeRequests().antMatchers("/product/add").access("hasRole('ADMIN')");
        //

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authenticationProvider());
    }
}
