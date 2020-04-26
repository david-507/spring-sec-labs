package org.david.sec.config;

import lombok.RequiredArgsConstructor;
import org.david.sec.security.basic.CustomBasicAuthEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomBasicAuthEntryPoint basicAuthEntryPoint;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .authenticationEntryPoint(basicAuthEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/hello").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .anyRequest().authenticated()
            .and()
        .csrf().disable();

        http.exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler);
    }
}
