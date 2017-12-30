package com.spring.boot.security.models;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends AppUsers implements UserDetails{

	private static final long serialVersionUID = 1L;

	public CustomUserDetails(AppUsers user){
		super(user);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRolesList()
		.stream()
		.map(role->
			new SimpleGrantedAuthority("ROLE_"+role.getRoleName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
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
