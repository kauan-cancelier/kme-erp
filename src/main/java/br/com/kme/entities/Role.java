package br.com.kme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Role")
@Table(name = "roles")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
    private Integer id;

	@Column(name = "name")
    @Size(max = 150, min = 3, message = "O nome deve conter entre 3 e 100 caracteres. ")
    @NotBlank(message = "O nome é obrigatório. ")
    private String name;
	
}
