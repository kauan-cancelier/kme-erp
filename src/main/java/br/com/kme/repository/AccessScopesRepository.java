package br.com.kme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.kme.entities.AccessScope;

public interface AccessScopesRepository extends JpaRepository<AccessScope, Integer> {

	@Query("SELECT as "
			+ "FROM AccessScope as "
			+ "WHERE as.id = :id")
	public AccessScope findBy(Integer id);
	
	@Modifying
	@Query("DELETE "
			+ "FROM AccessScope as "
			+ "WHERE as.id = :id")
	public AccessScope deleteBy(Integer id);
}
