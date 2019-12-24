package ba.sake.ss.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ba.sake.ss.userservice.security.RestAuthenticationEntryPoint;
import ba.sake.ss.userservice.security.RestAuthenticationFailureHandler;
import ba.sake.ss.userservice.security.RestAuthenticationSuccessHandler;
import ba.sake.ss.userservice.security.RestLogoutSuccessHandler;

/**
 * https://crazygui.wordpress.com/2014/08/29/secure-rest-services-using-spring-security/
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint authEntryPoint;
    @Autowired
    private RestAuthenticationSuccessHandler authSuccessHandler;
    @Autowired
    private RestAuthenticationFailureHandler authFailureHandler;
    @Autowired
    private RestLogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
            .formLogin()
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .permitAll()
                .and()
            .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        // @formatter:on
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
