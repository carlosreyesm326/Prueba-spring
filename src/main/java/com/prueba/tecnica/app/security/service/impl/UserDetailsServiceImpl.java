package com.prueba.tecnica.app.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.app.security.models.Usuario;
import com.prueba.tecnica.app.security.repository.UsuarioRepository;
import com.prueba.tecnica.app.security.utils.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 Usuario user = usuarioRepository.findOneByUsername(username).orElseThrow(()-> new UsernameNotFoundException("El usuario " + username + " no fue encontrado"));
		 
		 return new UserDetailsImpl(user);
	}

}
