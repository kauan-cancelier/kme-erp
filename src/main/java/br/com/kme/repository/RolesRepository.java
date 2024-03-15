package br.com.kme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.kme.entities.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
	
	@Query("SELECT r "
			+ "FROM Role r "
			+ "WHERE r.id = :id")
	public Role findBy(Integer id);
	
	@Query("SELECT r "
			+ "FROM Role r "
			+ "WHERE r.name = :name")
	public Role findBy(String name);
	
	@Modifying
	@Query("DELETE "
			+ "FROM Role r "
			+ "WHERE r.id = :id")
	public void deleteBy(Integer id);
	
	@Query("SELECT r "
			+ "FROM Role r "
			+ "WHERE (:name IS NULL OR r.name LIKE %:name%) "
			+ "ORDER BY r.name")
	public Page<Role> list(@Param("name") String name, Pageable pageable);

}
