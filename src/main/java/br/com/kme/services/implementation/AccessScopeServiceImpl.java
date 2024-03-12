package br.com.kme.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.AccessScope;
import br.com.kme.entities.User;
import br.com.kme.repository.AccessScopesRepository;
import br.com.kme.services.AccessScopeService;

@Service
public class AccessScopeServiceImpl implements AccessScopeService {
	
	@Autowired
	private AccessScopesRepository accessScopesRepository;

	@Override
	public AccessScope save(AccessScope accessScope) {
		Preconditions.checkNotNull(accessScope, "O escopo de acesso é obrigatório para salvar. ");
		return accessScopesRepository.save(accessScope);
	}

	@Override
	public AccessScope deleteBy(Integer id) {
		findBy(id);
		return accessScopesRepository.deleteBy(id);
	}

	@Override
	public AccessScope findBy(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		AccessScope accessScope = accessScopesRepository.findBy(id);
		Preconditions.checkNotNull(id, "Nenhum escopo de acesso encontrado para o id informado. ");
		return accessScope;
	}

	@Override
	public List<AccessScope> list() {
		List<AccessScope> accessScopes = accessScopesRepository.list();
		Preconditions.checkNotNull(accessScopes, "Nenhum escopo de acesso encontrado. ");
		return accessScopes;
	}

	@Override
	public List<AccessScope> listBy(User user) {
		Preconditions.checkNotNull(user, "O usuário é obrigatório. ");
		List<AccessScope> accessScopes = accessScopesRepository.listBy(user);
		Preconditions.checkNotNull(accessScopes, "Nenhum escopo de acesso encontrado para o usuário informado. ");
		return accessScopes;
	}

}
