package com.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class MySecurityConfiguration {

	
	@Autowired
	private UserDetailsService customDetailsService;
 	
 	@Autowired
 	private DataSource dataSource;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    	{
    		auth.userDetailsService(customDetailsService).passwordEncoder(passwordEncoder());
    		
    	}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http.headers().frameOptions().sameOrigin().and().authorizeRequests().requestMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
        .requestMatchers("/").permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/")
        .defaultSuccessUrl("/home").failureUrl("/login/error");
 
        return http.build();
    }
 
}
