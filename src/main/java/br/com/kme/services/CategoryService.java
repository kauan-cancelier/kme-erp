package br.com.kme.services;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.kme.entities.Category;

@Validated
public interface CategoryService {

	public Category save(Category category);

	public Category deleteBy(Integer id);

	public Category findBy(Integer id);
	
	public List<Category> listBy(String name);
	
}
