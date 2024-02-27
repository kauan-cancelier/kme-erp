package br.com.kme.services;

import br.com.kme.entities.AccessScope;

public interface AccessScopeService {

	public AccessScope save(AccessScope accessScope);

	public AccessScope deleteBy(Integer id);

	public AccessScope findBy(Integer id);
}
