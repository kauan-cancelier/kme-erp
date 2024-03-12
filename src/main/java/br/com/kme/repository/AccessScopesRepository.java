package br.com.kme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kme.entities.AccessScope;
import br.com.kme.entities.User;

public interface AccessScopesRepository extends JpaRepository<AccessScope, Integer> {

	@Query("SELECT as "
			+ "FROM AccessScope as "
			+ "WHERE as.id = :id")
	public AccessScope findBy(@Param("id")Integer id);
	
	@Modifying
	@Query("DELETE "
			+ "FROM AccessScope as "
			+ "WHERE as.id = :id")
	public AccessScope deleteBy(@Param("id")Integer id);
	
	@Query("SELECT as "
			+ "FROM AccessScope as ")
	public List<AccessScope> list();
	
	@Query("SELECT as "
			+ "FROM AccessScope as "
			+ "WHERE as.user = :user ")
	public List<AccessScope> listBy(@Param("user")User user);
	
}
