package br.com.kme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "User")
@Table(name = "users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends EntityBase implements Validated {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
    private Integer id;

	@Column(name = "name")
	@NotBlank(message = "O nome é obrigatório. ")
    @Size(max = 100, min = 3, message = "O nome deve conter entre 3 e 100 caracteres. ")
    private String name;

    @Column(name = "email")
    @NotBlank(message = "O email é obrigatório. ")
    @Size(max = 100, min = 3, message = "O email deve conter entre 3 e 100 caracteres. ")
    @Email(message = "O email deve ter o formato: example@email.com")
    @Email
    private String email;
    
    @Column(name = "password")
    @NotBlank(message = "A senha é obrigatória. ")
    @Size(max = 100, min = 3, message = "A senha deve ter entre 3 e 100 caracteres. ")
    private String password;
    
    @JoinColumn(name = "role_id")
    @NotNull(message =  "O papel é obrigatório. ")
    @ManyToOne
    private Role role;
    
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