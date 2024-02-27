package br.com.kme.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kme.entities.User;
import br.com.kme.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody User user) {
		try {
			User savedUser = userService.save(user);			
			return ResponseEntity.created(URI.create("/users/id/" + savedUser.getId())).build();
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findBy(@PathVariable("id") Integer id) {
		User findedUser = userService.findBy(id);
		return ResponseEntity.ok(findedUser);
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteBy(@PathVariable("id") Integer id) {
		User deletedUser = userService.deleteBy(id);
		return ResponseEntity.ok(deletedUser);
	}
	
}