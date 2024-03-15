package br.com.kme.dto;

import br.com.kme.entities.Role;
import br.com.kme.entities.Validated;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class UserDto implements Validated {
	
    private Integer id;

    private String name;

    private String cpf;
    
    private String email;
    
    private String phoneNumber;
    
    private String jobTitle;
    
    private Role role;
    
    @Transient
	@Override
	public boolean isPersisted() {
		return getId() != null && getId() > 0;
	}
    
}
