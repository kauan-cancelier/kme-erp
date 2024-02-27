package br.com.kme.services;

import org.springframework.validation.annotation.Validated;

import br.com.kme.entities.Permission;

@Validated
public interface PermissionService {

	public Permission save(Permission permission);

	public Permission deleteBy(Integer id);

	public Permission findBy(Integer id);
	
}
