package br.com.kme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.kme.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u " 
			+ "FROM User u " 
			+ "WHERE u.id = :id")
	public User findBy(@Param("id") Integer id);
	
	@Modifying
	@Query("DELETE "
			+ "FROM User u "
			+ "WHERE u.id = :id")
	public void deleteBy(@Param("id") Integer id);
	
	@Query("SELECT u "
			+ "FROM User u "
			+ "WHERE u.email = :email "
			+ "AND u.password = :password")
	public User login(@Param("email") String email, @Param("password") String password);
	
	@Query("SELECT u "
			+ "FROM User u "
			+ "WHERE u.email = :email")
	public User findBy(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE (:name IS NULL OR u.name LIKE %:name%) ORDER BY u.name")
	public Page<User> list(@Param("name") String name, Pageable pageable);

	
}