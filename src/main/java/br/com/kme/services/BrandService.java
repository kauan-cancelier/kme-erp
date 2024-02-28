package br.com.kme.services;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.kme.entities.Brand;

@Validated
public interface BrandService {
	
	public Brand save(Brand brand);

	public Brand deleteBy(Integer id);

	public Brand findBy(Integer id);
	
	public List<Brand> listBy(String name);
	
}
