package br.com.kme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kme.entities.Category;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {
	
	@Query("SELECT c " 
			+ "FROM Category c " 
			+ "WHERE c.id = :id")
	public Category findBy(@Param("id") Integer id);
	
	@Modifying
	@Query("DELETE "
			+ "FROM Category c "
			+ "WHERE c.id = :id")
	public void deleteBy(@Param("id") Integer id);
	
	@Query("SELECT c FROM Category c WHERE (:name IS NULL OR c.name LIKE %:name%)")
	public List<Category> list(@Param("name") String name);
	
}
