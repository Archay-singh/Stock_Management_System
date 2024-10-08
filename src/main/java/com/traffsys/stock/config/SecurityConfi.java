package com.traffsys.stock.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.traffsys.stock.Service.CustomUserDetailsServiceImpl;
import com.traffsys.stock.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfi {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsServiceImpl();
	}

	@Bean   //allow path only
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cor -> cor.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/auth/login","/api/user/createUser").permitAll().anyRequest().authenticated())
				.sessionManagement(
						sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();  
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean(name = "customPasswordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//allowing all origins, methods, and headers
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); //*-->means any origin can make any request
        configuration.setAllowedMethods(Arrays.asList("*"));
        
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
























/*
authorizeHttpRequests: Configures which requests should be authorized.
requestMatchers("/auth/login").permitAll(): Allows everyone to access the login endpoint without authentication.
anyRequest().authenticated(): Requires authentication for all other requests.

Disable CSRF protection for stateless APIs.
Allow access to the login endpoint without authentication.
Require authentication for all other requests.
Set session management to stateless.
Add the custom JWT filter to the security filter chain.


This class sets up the security for a Spring Boot application by defining how users are authenticated, 
which endpoints are protected, and how user details are retrieved and validated. 
It uses JWT for stateless authentication, ensuring secure access to the application.

*/


//DaoAuthenticationProvider: A built-in provider that retrieves user details from a database or other storage.




