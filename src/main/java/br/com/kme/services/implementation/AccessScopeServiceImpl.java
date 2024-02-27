package br.com.kme.services.implementation;

import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.AccessScope;
import br.com.kme.repository.AccessScopesRepository;
import br.com.kme.services.AccessScopeService;

@Service
public class AccessScopeServiceImpl implements AccessScopeService {
	
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

}
