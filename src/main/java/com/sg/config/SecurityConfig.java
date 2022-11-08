package com.sg.config;

import com.sg.source.function.login.loginService.impl.CustomUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailServiceImpl customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        String[] resources = new String[]{
                "/js/**", "/css/**", "/resources/**", "/webjars/**"
        };

        http
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                //.antMatchers("/admin/**").hasAnyRole("ADMIN")
                //.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .permitAll();
                /*.and()
                .csrf().disable();*/

        /*http
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .exceptionHandling()
                .and();*/
    }

    /*로그인 핸들러*/
    /*@Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/getMainByUser");//default로 이동할 url
    }*/

}
