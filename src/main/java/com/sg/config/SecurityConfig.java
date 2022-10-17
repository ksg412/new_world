package com.sg.config;

import com.sg.source.function.login.loginService.impl.CustomUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailServiceImpl customUserDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/resources/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                //.loginPage("/login")
                //.loginProcessingUrl("/loginProcess")
                //.usernameParameter("username")
                //.passwordParameter("password")
                //.defaultSuccessUrl("/getMainByUser")
                //.successHandler(successHandler())
                //.failureHandler()
                //.failureForwardUrl("/login?errorPage")
                //.failureUrl("/login?error")
                .and()
            .logout()
                //.logoutUrl("/logout")
                //.logoutSuccessUrl("/login")
                //.permitAll()
                .and()
            .exceptionHandling()
                //.accessDeniedPage("/app/error")
                .and();
            //.csrf()
                //.ignoringAntMatchers("/logout", "/login")
               //.disable();
    }

    /*비밀번호 암호화*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /*로그인 핸들러*/
    /*@Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/getMainByUser");//default로 이동할 url
    }*/

}
