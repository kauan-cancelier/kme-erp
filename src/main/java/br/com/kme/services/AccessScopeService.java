package br.com.kme.services;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.com.kme.entities.AccessScope;
import br.com.kme.entities.User;

@Validated
public interface AccessScopeService {

	public AccessScope save(AccessScope accessScope);

	public AccessScope deleteBy(Integer id);

	public AccessScope findBy(Integer id);
	
	public List<AccessScope> listBy(User user);
	
	public List<AccessScope> list();
	
}
