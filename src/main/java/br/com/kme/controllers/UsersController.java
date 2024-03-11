package br.com.kme.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import br.com.kme.dto.UserDto;
import br.com.kme.entities.User;
import br.com.kme.services.UserService;
import br.com.kme.utils.DtoConverter;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody User user) {
		User savedUser = userService.save(user);
		return ResponseEntity.created(URI.create("/users/" + savedUser.getId())).build();
	}

	@PutMapping
	public ResponseEntity<?> edit(@RequestBody UserDto userDto) {
		Preconditions.checkArgument(userDto.isPersisted(), "O usuário deve possuir id para alteração. ");
		User findedUser = userService.findBy(userDto.getId());
		findedUser.setCpf(userDto.getCpf());
		findedUser.setName(userDto.getName());
		findedUser.setJobTitle(userDto.getJobTitle());
		findedUser.setPhoneNumber(userDto.getPhoneNumber());
		return ResponseEntity.ok(userService.save(findedUser));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findBy(@PathVariable("id") Integer id) {
		User findedUser = userService.findBy(id);
		return ResponseEntity.ok(DtoConverter.serialize(findedUser, UserDto.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBy(@PathVariable("id") Integer id) {
		User deletedUser = userService.deleteBy(id);
		return ResponseEntity.ok(deletedUser);
	}

	@GetMapping
	public ResponseEntity<?> list(@PathParam("name") String name, @PathParam("page") Pageable pageable) {
		Page<User> usersPage = userService.listBy(name, pageable);
		Page<UserDto> usersDtoPage = DtoConverter.serialize(usersPage, UserDto.class);
		return ResponseEntity.ok(usersDtoPage);
	}

}