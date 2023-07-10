package com.prueba.tecnica.app.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.tecnica.app.security.utils.CredencialesDto;
import com.prueba.tecnica.app.security.utils.UserDetailsImpl;
import com.prueba.tecnica.app.utils.AppConstant;

import net.bytebuddy.asm.Advice.Return;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse reponse) throws AuthenticationException {

		CredencialesDto credentials = new CredencialesDto();

		try {
			credentials = new ObjectMapper().readValue(request.getReader(), CredencialesDto.class);
		}catch (IOException e) {


		}

		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken( 
				credentials.getUsername(), 
				credentials.getPassword(),
				Collections.emptyList());

		return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		
		UserDetailsImpl userDefault = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDefault.getUsername(), userDefault.getPassword());
		
		response.addHeader(AppConstant.AUTHORIZATION,AppConstant.PREFIX_TOKEN+token);
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	} 

}
