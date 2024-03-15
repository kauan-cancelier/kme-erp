package br.com.kme.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.kme.entities.Role;

@Validated
public interface RoleService {

	public Role save(Role role);

	public Role deleteBy(Integer id);

	public Role findBy(Integer id);
	
	public Role findBy(String name);
	
	public Page<Role> listBy(String name, Pageable pageable);
	
	
}
