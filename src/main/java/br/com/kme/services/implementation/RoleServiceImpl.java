package br.com.kme.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.Role;
import br.com.kme.repository.RolesRepository;
import br.com.kme.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RolesRepository rolesRepository;
	
	@Override
	public Role save(Role role) {
		Preconditions.checkNotNull(role, "O papel do usuáio é informado para salvar. ");
		return rolesRepository.save(role);
	}

	@Override
	public Role deleteBy(Integer id) {
		findBy(id);
		return rolesRepository.deleteBy(id);
	}

	@Override
	public Role findBy(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		Role role = rolesRepository.findBy(id);
		Preconditions.checkNotNull(id, "Nenhum papel do usuário encontrado para o id informado. ");
		return role;
	}

}
