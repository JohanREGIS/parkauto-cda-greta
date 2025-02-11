package com.parkauto.rest.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.parkauto.rest.service.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl implements JWTService {
	
	@Override
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername())	// Création du Token en utilisant l'username
				.setIssuedAt(new Date(System.currentTimeMillis()))	//On récupère la date de création du Token
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24)) // Date d'expiration du Token = date de création + 1 jour (1000*60*60*24) en millisec (*7 pour 7jours)
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	@Override
	public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+604800000))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public Key getSigningKey() {
		byte[] key = Decoders.BASE64
				.decode("1b8df388d163a550b5364e5b61cc606322c891e9ba7d0534e7e0720c0fb65e9c");
		return Keys.hmacShaKeyFor(key);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
				.parseClaimsJws(token).getBody();
	}
	
    @Override
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
}
