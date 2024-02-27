package br.com.kme.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kme.dto.LoginDto;
import br.com.kme.entities.User;
import br.com.kme.exceptions.ApiError;
import br.com.kme.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto login) {
		try {
			String email = login.getEmail();
			String password = login.getPassword();
			User foundUser = userService.login(email, password);
			return ResponseEntity.ok(URI.create("/users/id/" + foundUser.getId()));
		} catch (Exception e) {
			throw new ApiError(400, "Email e ou senha inválidos");
		}
	}
}
