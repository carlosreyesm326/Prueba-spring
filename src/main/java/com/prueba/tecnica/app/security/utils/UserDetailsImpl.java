package com.prueba.tecnica.app.security.utils;


import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.prueba.tecnica.app.security.models.Usuario;

public class UserDetailsImpl implements UserDetails {

	private final Usuario usuario;
	
	
	
	public UserDetailsImpl(Usuario usuario) {
		super();
		this.usuario = usuario;
	}



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

}
