package br.com.kme.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.kme.entities.User;
import br.com.kme.repository.UsersRepository;
import br.com.kme.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersRepository usersRepository;

	
	@Override
	public User save(User user) {
		Preconditions.checkNotNull(user, "O usuário é obrigatório para salvar. ");
		Preconditions.checkArgument(usersRepository.findBy(user.getEmail()) == null, "O email informado '" + user.getEmail() +"' já está em uso.");
		return usersRepository.save(user);
	}

	@Override
	public User deleteBy(Integer id) {
		findBy(id);
		return usersRepository.deleteBy(id);
	}

	@Override
	public User findBy(Integer id) {
		Preconditions.checkNotNull(id, "O id é obrigatório. ");
		User user = usersRepository.findBy(id);
		Preconditions.checkNotNull(user, "Nenhum usuário encontrado para o id informado.");
		return user;
	}

	@Override
	public User login(String email, String password) {
		Preconditions.checkArgument(!email.isBlank(), "O email é obrigatório para logar. ");
		Preconditions.checkArgument(!password.isBlank(), "A senha é obrigatória para logar. ");
		User user = usersRepository.login(email, password);
		Preconditions.checkNotNull(user, "Nenhum usuário encontrado para o email e senha informados. ");
		return user;
	}

}
