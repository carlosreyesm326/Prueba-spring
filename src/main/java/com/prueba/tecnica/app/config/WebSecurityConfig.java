package com.prueba.tecnica.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.prueba.tecnica.app.security.JWTAuthenticationFilter;
import com.prueba.tecnica.app.security.JWTAuthorizationFilter;

@Configuration
public class WebSecurityConfig {

	
	private final UserDetailsService userDetailsService;
	

	private final JWTAuthorizationFilter jwtAuthorizationFilter;

	
	
	
	public WebSecurityConfig(UserDetailsService userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager autManager) throws Exception {
		
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter();
		authenticationFilter.setAuthenticationManager(autManager);
		authenticationFilter.setFilterProcessesUrl("/login");
		
		
		return http
				.csrf()
				.disable()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(authenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	


	
	@Bean
	AuthenticationManager autManager (HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	public static void main(String[] args) {
		System.out.println("pass: "+ new BCryptPasswordEncoder().encode("pass123534543534"));
	}
	
}
