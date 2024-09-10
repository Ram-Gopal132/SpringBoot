package com.app.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails ram=User.builder().username("Gopal").password(passwordEncoder().encode("ram123")).roles("USER").build();
		
		UserDetails admin=User.builder().username("admin").password(passwordEncoder().encode("admin236")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(ram,admin);

	}
	
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception
	{
		httpSecurity.csrf(csrf ->csrf.disable()).authorizeHttpRequests((authorize)->{
			authorize.anyRequest().authenticated();
		}
		).formLogin(Customizer.withDefaults());
		return httpSecurity.build();
	}
}
