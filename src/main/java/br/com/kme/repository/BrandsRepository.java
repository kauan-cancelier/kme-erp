package br.com.kme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kme.entities.Brand;

public interface BrandsRepository extends JpaRepository<Brand, Integer> {
	@Query("SELECT b " 
			+ "FROM Brand b " 
			+ "WHERE b.id = :id")
	public Brand findBy(@Param("id") Integer id);
	
	@Modifying
	@Query("DELETE "
			+ "FROM Brand b "
			+ "WHERE b.id = :id")
	public void deleteBy(@Param("id") Integer id);
	
	@Query("SELECT b FROM Brand b WHERE (:name IS NULL OR b.name LIKE %:name%)")
	public List<Brand> list(@Param("name") String name);
	
}
