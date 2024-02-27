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

import br.com.kme.entities.Permission;
import br.com.kme.services.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {
	
	@Autowired
	private PermissionService permissionService;
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Permission permission) {
		Permission savedPermission = permissionService.save(permission);
		return ResponseEntity.created(URI.create("/permissions/id/" + savedPermission.getId())).build();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findBy(@PathVariable("id") Integer id) {
		Permission findedPermission = permissionService.findBy(id);
		return ResponseEntity.ok(findedPermission);
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteBy(@PathVariable("id") Integer id) {
		Permission deletedPermission = permissionService.deleteBy(id);
		return ResponseEntity.ok(deletedPermission);
	}
	
}