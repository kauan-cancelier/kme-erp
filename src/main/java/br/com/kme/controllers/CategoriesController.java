package br.com.kme.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.kme.entities.Category;
import br.com.kme.services.CategoryService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Category category) {
		Category savedCategory = categoryService.save(category);
		return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId())).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findBy(@PathVariable("id") Integer id) {
		Category findedCategory = categoryService.findBy(id);
		return ResponseEntity.ok(findedCategory);
	}

	@GetMapping
	public ResponseEntity<?> list(@PathParam("name") String name) {
		List<Category> categories = categoryService.listBy(name);
		return ResponseEntity.ok(categories);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBy(@PathVariable("id") Integer id) {
		Category deletedCategory = categoryService.deleteBy(id);
		return ResponseEntity.ok(deletedCategory);
	}

	@PutMapping
	public ResponseEntity<?> edit(@RequestBody Category category) {
		Preconditions.checkArgument(category.isPersisted(), "A categoria deve possuir id para alteração. ");
		return ResponseEntity.ok(categoryService.save(category));
	}

}
