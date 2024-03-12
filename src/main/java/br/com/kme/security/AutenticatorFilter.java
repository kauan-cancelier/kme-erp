package br.com.kme.security;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.kme.services.implementation.AccessCredentialsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticatorFilter extends OncePerRequestFilter {

	@Autowired
	private TokenJwtManager tokenManager;
	
	@Autowired
	private AccessCredentialsServiceImpl service;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String login = null;
		
		boolean isHeaderAuthorizationPresent = authHeader != null && authHeader.startsWith("Bearer ");
		if (isHeaderAuthorizationPresent) {
			token = authHeader.substring(7);
			login = tokenManager.extractEmail(token);
		}
		
		boolean isNewLogin = login != null 
				&& SecurityContextHolder.getContext().getAuthentication() == null;
		
		if (isNewLogin) {
			UserDetails credential = service.loadUserByUsername(login);
			if (tokenManager.isValid(token, credential)) {
				UsernamePasswordAuthenticationToken authenticatedToken = 
						new UsernamePasswordAuthenticationToken(credential, null, credential.getAuthorities());
				authenticatedToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	
	
	
	
}
