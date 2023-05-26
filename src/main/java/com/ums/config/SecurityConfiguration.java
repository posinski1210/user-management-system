package com.ums.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;


    @Autowired
    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery("SELECT email AS username, password, true FROM \"user\" WHERE email=?")
                .authoritiesByUsernameQuery("SELECT u.email AS username, r.role AS authority FROM \"user\" u INNER JOIN user_role ur ON (u.user_id = ur.user_id) INNER JOIN role r ON (ur.role_id = r.role_id) WHERE u.email=?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register", "/", "/login", "/about", "/css/**", "/webjars/**").permitAll()
                .antMatchers("/profile/**", "/tasks/**", "/task/**", "/users", "/user/**", "/h2-console/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/assignment/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/profile")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
