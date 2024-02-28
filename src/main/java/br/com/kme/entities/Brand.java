package br.com.kme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Brand")
@Table(name = "brands")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Brand implements Validated {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name = "name")
	@Size(max = 100, min = 3, message = "O nome deve conter entre 3 e 100 caracteres. ")
	@NotBlank(message = "O nome é obrigatório. ")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Transient
	@Override
	public boolean isPersisted() {
		return getId() != null && getId() > 0;
	}
}
