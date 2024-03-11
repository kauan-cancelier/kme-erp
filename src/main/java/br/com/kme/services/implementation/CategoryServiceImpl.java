package br.com.kme.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.Category;
import br.com.kme.repository.CategoriesRepository;
import br.com.kme.services.CategoryService;
import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Override
	public Category save(Category category) {
		Preconditions.checkNotNull(category, "A categoria é obrigatória para salvar.");
		return categoriesRepository.save(category);
	}

	@Override
	@Transactional
	public Category deleteBy(Integer id) {
		Category category = findBy(id);
		categoriesRepository.deleteBy(id);
		return category;
	}

	@Override
	public Category findBy(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		Category category = categoriesRepository.findBy(id);
		Preconditions.checkNotNull(category, "Nenhuma categoria encontrada para o id informado.");
		return category;
	}

	@Override
	public List<Category> listBy(String name) {
		return categoriesRepository.list(name);
	}
}
