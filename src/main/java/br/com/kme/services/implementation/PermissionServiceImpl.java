package br.com.kme.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.Permission;
import br.com.kme.repository.PermissionsRepository;
import br.com.kme.services.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionsRepository permissionsRepository;

	@Override
	public Permission save(Permission permission) {
		Preconditions.checkNotNull(permission, "A permissão é obrigatória para salvar. ");
		return permissionsRepository.save(permission);
	}

	@Override
	public Permission deleteBy(Integer id) {
		findBy(id);
		return permissionsRepository.deleteBy(id);
	}

	@Override
	public Permission findBy(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		Permission permission = permissionsRepository.findBy(id);
		Preconditions.checkNotNull(id, "Nenhum permissão encontrada para o id informado. ");
		return permission;
	}

	@Override
	public String[] permissionList() {
		// TODO Auto-generated method stub
		return null;
	}

}
