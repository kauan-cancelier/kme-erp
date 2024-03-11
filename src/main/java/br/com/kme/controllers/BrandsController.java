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

import br.com.kme.entities.Brand;
import br.com.kme.services.BrandService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/brands")
public class BrandsController {

	@Autowired
	private BrandService brandService;

	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody Brand brand) {
		Brand savedBrand = brandService.save(brand);
		return ResponseEntity.created(URI.create("/brands/" + savedBrand.getId())).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findBy(@PathVariable("id") Integer id) {
		Brand findedBrand = brandService.findBy(id);
		return ResponseEntity.ok(findedBrand);
	}

	@GetMapping
	public ResponseEntity<?> list(@PathParam("name") String name) {
		List<Brand> brands = brandService.listBy(name);
		return ResponseEntity.ok(brands);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBy(@PathVariable("id") Integer id) {
		Brand deletedBrand = brandService.deleteBy(id);
		return ResponseEntity.ok(deletedBrand);
	}

	@PutMapping
	public ResponseEntity<?> edit(@RequestBody Brand brand) {
		Preconditions.checkArgument(brand.isPersisted(), "A marca deve possuir id para alteração. ");
		return ResponseEntity.ok(brandService.save(brand));
	}

}
