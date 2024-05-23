package com.app.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.app.service.services.UserService;
import com.app.service.security.auth.RestAuthenticationEntryPoint;
import com.app.service.security.auth.TokenAuthenticationFilter;
import com.app.service.util.TokenUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {


	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	
	@Autowired
	private TokenUtils tokenUtils;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
	        .and()
	        .authorizeRequests()
	            .antMatchers("/auth/**").permitAll() // Dozvoli pristup svim endpointima za autentifikaciju
	            .antMatchers(HttpMethod.POST, "/api/users/register").permitAll() // Dozvoli pristup endpointu za registraciju bez autentifikacije
	            .anyRequest().authenticated() // Zahtevaj autentifikaciju za sve ostale endpointe
	        .and()
	        .cors()
	        .and()
	        .csrf().disable()
	        .headers().frameOptions().disable()
	        .and()
	        .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userDetailsService()), BasicAuthenticationFilter.class);

	    http.authenticationProvider(authenticationProvider());

	    return http.build();
	}

	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/api/users/register")
				.antMatchers(HttpMethod.GET, "/api/fruits/phValues", "/api/fruits/calcValues", "/zemljiste", "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html", "/**/*.css",
						"/**/*.js");

	}

}
