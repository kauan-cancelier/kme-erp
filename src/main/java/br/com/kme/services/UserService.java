package br.com.kme.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.kme.entities.User;

@Validated
public interface UserService {

	public User save(User user);

	public User deleteBy(Integer id);

	public User findBy(Integer id);
	
	public User findBy(String email);
	
	public User login(String email, String password);
	
	public Page<User> listBy(String name, Pageable pageable);
	
}