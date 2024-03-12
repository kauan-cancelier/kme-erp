package br.com.kme.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.kme.entities.Role;
import br.com.kme.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCredentials implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String login;

	private String pass;

	private List<GrantedAuthority> permissions;

	public AccessCredentials(User user, Role role) {
		this.login = user.getEmail();
		this.pass = user.getPassword();
		this.permissions = new ArrayList<GrantedAuthority>();
		this.permissions.add(new SimpleGrantedAuthority(role.getName()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getPermissions();
	}

	@Override
	public String getPassword() {
		return this.getPass();
	}

	@Override
	public String getUsername() {
		return this.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
