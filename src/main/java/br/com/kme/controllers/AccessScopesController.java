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

import br.com.kme.entities.AccessScope;
import br.com.kme.services.AccessScopeService;

@RestController
@RequestMapping("/access_scopes")
public class AccessScopesController {
	
	@Autowired
	private AccessScopeService accessScopeService;
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody AccessScope accessScope) {
		AccessScope savedAccessScope = accessScopeService.save(accessScope);
		return ResponseEntity.created(URI.create("/accessScopes/" + savedAccessScope.getId())).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findBy(@PathVariable("id") Integer id) {
		AccessScope findedAccessScope = accessScopeService.findBy(id);
		return ResponseEntity.ok(findedAccessScope);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBy(@PathVariable("id") Integer id) {
		AccessScope deletedAccessScope = accessScopeService.deleteBy(id);
		return ResponseEntity.ok(deletedAccessScope);
	}
	
}