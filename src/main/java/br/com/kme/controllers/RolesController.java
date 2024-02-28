package br.com.kme.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kme.entities.Role;
import br.com.kme.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RolesController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Role role) {
		Role savedRole = roleService.save(role);
		return ResponseEntity.created(URI.create("/roles/" + savedRole.getId())).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findBy(@PathVariable("id") Integer id) {
		Role findedRole = roleService.findBy(id);
		return ResponseEntity.ok(findedRole);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBy(@PathVariable("id") Integer id) {
		Role deletedRole = roleService.deleteBy(id);
		return ResponseEntity.ok(deletedRole);
	}
	
}
