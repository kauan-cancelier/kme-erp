package br.com.kme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.kme.entities.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
	
	@Query("SELECT r "
			+ "FROM Role r "
			+ "WHERE r.id = :id")
	public Role findBy(Integer id);
	
	@Modifying
	@Query("DELETE "
			+ "FROM Role r "
			+ "WHERE r.id = :id")
	public Role deleteBy(Integer id);

}
