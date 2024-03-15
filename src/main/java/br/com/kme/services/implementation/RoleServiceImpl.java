package br.com.kme.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.Role;
import br.com.kme.entities.User;
import br.com.kme.repository.RolesRepository;
import br.com.kme.repository.UsersRepository;
import br.com.kme.services.RoleService;
import jakarta.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public Role save(Role role) {
		Preconditions.checkNotNull(role, "O papel do usuário é informado para salvar. ");
		if (role.isPersisted()) {
			return rolesRepository.save(role);
		}
		Preconditions.checkArgument(rolesRepository.findBy(role.getName()) == null, "Já existe um papel com esse nome.");
		return rolesRepository.save(role);
	}

	@Override
	@Transactional
	public Role deleteBy(Integer id) {
		Role role = findBy(id);
		User user = usersRepository.findBy(role);
		Preconditions.checkArgument(user == null, "Não é possivel excluir pois existem usuários vinculados com este perfil. ");
		rolesRepository.deleteBy(role.getId());
		return role;
	}

	@Override
	public Role findBy(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		Role role = rolesRepository.findBy(id);
		Preconditions.checkNotNull(id, "Nenhum papel do usuário encontrado para o id informado. ");
		return role;
	}
	
	@Override
	public Role findBy(String name) {
		Preconditions.checkNotNull(name, "O nome é obrigatório. ");
		Role role = rolesRepository.findBy(name);
		Preconditions.checkNotNull(name, "Nenhum papel do usuário encotnrado para o nome informado. ");
		return role;
	}
	
	@Override
	public Page<Role> listBy(String name, Pageable pageable) {
		return rolesRepository.list(name, pageable);
	}

}
