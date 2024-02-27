package br.com.kme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "User")
@Table(name = "users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Validated {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
    private Integer id;

	@Column(name = "name")
    @Size(max = 100, min = 3, message = "O nome deve conter entre 3 e 100 caracteres. ")
    @NotBlank(message = "O nome é obrigatório. ")
    private String name;

    @Column(name = "email")
    @Size(max = 100, min = 3, message = "O email deve conter entre 3 e 100 caracteres. ")
    @Email(message = "O email deve ter o formato: example@email.com")
    @NotBlank(message = "O email é obrigatório. ")
    @Email
    private String email;
    
    @Column(name = "password")
    @Size(max = 100, min = 3, message = "A senha deve ter entre 3 e 100 caracteres. ")
    @NotBlank(message = "A senha é obrigatória. ")
    private String password;
    
    @Column(name = "cpf")
    private String cpf;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "job_title")
    private String jobTitle;
    
    @Transient
	@Override
	public boolean isPersisted() {
		return getId() != null && getId() > 0;
	}

}