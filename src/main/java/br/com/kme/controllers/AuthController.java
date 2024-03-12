package br.com.kme.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.kme.dto.LoginDto;
import br.com.kme.entities.User;
import br.com.kme.security.TokenJwtManager;
import br.com.kme.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private TokenJwtManager gerenciadoDeToken;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		Authentication authenticatedToken = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		Preconditions.checkArgument(authenticatedToken.isAuthenticated(), "Email e/ou senha invalidos. ");
		
		User user = userService.findBy(loginDto.getEmail());

		String generatedToken = gerenciadoDeToken.generate(
				user.getEmail(),
				user.getRole()
			);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("token", generatedToken);
		return ResponseEntity.ok(response);
	}
}