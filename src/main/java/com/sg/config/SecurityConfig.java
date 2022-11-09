package com.sg.config;

import com.sg.source.function.login.loginService.impl.CustomUserDetailServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(SecurityConfig.class);

    @Autowired
    CustomUserDetailServiceImpl customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        String[] resources = new String[]{
                "/common/**", "/image/**", "/lib/**"
        };

        logger.info("XXX configure HTTP User detail org.himanshu.service");

        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                //.antMatchers("/admin/**").hasAnyRole("ADMIN")
                //.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //
                //.defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll();
                /*.and()
                ;*/

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
