package br.com.kme.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
	
	@NotBlank(message = "O email é obrigatório.")
	@Email(message = "O email é inválido. ")
    private String email;
    
	@NotBlank(message = "A senha é obrigatória.")
    private String password;

}
