package br.com.kme.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.User;
import br.com.kme.repository.UsersRepository;
import br.com.kme.security.AccessCredentials;

@Service
public class AccessCredentialsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findBy(email);
		Preconditions.checkNotNull(user, "Não foi encontrado nenhum usuario para o email informado. ");
		return new AccessCredentials(user, user.getRole());
	}

}
