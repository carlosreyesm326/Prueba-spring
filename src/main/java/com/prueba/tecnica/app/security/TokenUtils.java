package com.prueba.tecnica.app.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.prueba.tecnica.app.utils.AppConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {




	public static String createToken(String nombre, String email) {
		long expirationTime = AppConstant.ACCESS_TOKEN_VALIDITY_SECONDS *1_000;
		Date expirationDate = new Date (System.currentTimeMillis()+expirationTime);
		Map<String,Object> extra = new HashMap<>();
		extra.put("name", nombre);


		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(Keys.hmacShaKeyFor(AppConstant.ACCESS_TOKEN_SECRET.getBytes()))
				.compact() ;


	}

	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {


			Claims claims = Jwts.parserBuilder()
					.setSigningKey(AppConstant.ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();



			String email = claims.getSubject();
			return new UsernamePasswordAuthenticationToken(email, null,java.util.Collections.emptyList());
		}catch (JwtException e) {
			return null;

		}
	}
}