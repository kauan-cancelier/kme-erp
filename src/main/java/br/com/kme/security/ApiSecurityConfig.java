package br.com.kme.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.kme.services.implementation.AccessCredentialsServiceImpl;

@Configuration
public class ApiSecurityConfig {

	@Autowired
	private AutenticatorFilter autenticatorFilter;

	private final AccessCredentialsServiceImpl accessCredentials = new AccessCredentialsServiceImpl();

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(accessCredentials);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.applyPermitDefaultValues();
		cors.setAllowedHeaders(Arrays.asList("*"));
		cors.setAllowedMethods(Arrays.asList("*"));
		cors.setAllowedOrigins(Arrays.asList("*"));
		cors.setExposedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
		ccs.registerCorsConfiguration("/**", cors);
		return ccs;
	}

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors().configurationSource(urlBasedCorsConfigurationSource()).and()
				.authorizeHttpRequests(
						request -> request
						.requestMatchers("/auth/**")
							.permitAll()
						.requestMatchers("/login/**")
							.permitAll()
						.requestMatchers("/users/**")
							.permitAll()
						.requestMatchers("/permissions/**")
							.permitAll()
						.requestMatchers("/access_scopes/**")
							.permitAll()
						.requestMatchers("/roles/**")
							.permitAll()	
				
							.anyRequest().authenticated())
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(autenticatorFilter, UsernamePasswordAuthenticationFilter.class)
				.cors(c -> urlBasedCorsConfigurationSource());
		return http.build();
	}

}