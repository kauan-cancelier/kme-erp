package br.com.kme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.kme.entities.Permission;

@Repository
public interface PermissionsRepository extends JpaRepository<Permission, Integer> {

	@Query("SELECT p "
			+ "FROM Permission p "
			+ "WHERE p.id = :id")
	public Permission findBy(Integer id);
	
	@Modifying
	@Query("DELETE "
			+ "FROM Permission p "
			+ "WHERE p.id = :id")
	public Permission deleteBy(Integer id);

}
