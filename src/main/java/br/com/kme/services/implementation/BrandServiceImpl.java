package br.com.kme.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.Brand;
import br.com.kme.repository.BrandsRepository;
import br.com.kme.services.BrandService;
import jakarta.transaction.Transactional;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandsRepository brandsRepository;
	
	@Override
	public Brand save(Brand brand) {
		Preconditions.checkNotNull(brand, "A marca é obrigatória para salvar. ");
		return brandsRepository.save(brand);
	}

	@Override
	@Transactional
	public Brand deleteBy(Integer id) {
		Brand brand = findBy(id);
		brandsRepository.deleteBy(id);
		return brand;
	}

	@Override
	public Brand findBy(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		Brand brand = brandsRepository.findBy(id);
		Preconditions.checkNotNull(brand, "Nenhuma marca encontrada para o id informado.");
		return brand;
	}

	@Override
	public List<Brand> listBy(String name) {
		return brandsRepository.list(name);
	}

}
