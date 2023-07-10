package com.prueba.tecnica.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.prueba.tecnica.app.utils.AppConstant;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
				String bearerToken = request.getHeader(AppConstant.AUTHORIZATION);
		
		if (bearerToken!= null && bearerToken.startsWith(AppConstant.PREFIX_TOKEN)) {
			String token = bearerToken.replace(AppConstant.PREFIX_TOKEN,AppConstant.EMPTY_STRING);
			UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(usernamePAT);
			
		}
		filterChain.doFilter(request, response);
	}

}
