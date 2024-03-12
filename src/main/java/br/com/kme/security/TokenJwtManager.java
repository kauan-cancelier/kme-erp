package br.com.kme.security;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.kme.entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenJwtManager {
	
	@Value("${spring.jwt.secret}")
	private String secret;
	
	@Value("${spring.jwt.ttl-in-millis}")
	private int ttlInMillis;

	public String generate(String login, Role role) {
	    return Jwts.builder()
	            .setClaims(new HashMap<>())
	            .claim("login", login)
	            .claim("role", role) 
	            .setSubject(login)
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + ttlInMillis))
	            .signWith(getAssignatureKey(), SignatureAlgorithm.HS256)
	            .compact();
	}
	
	public String extractEmail(String generatedToken) {
		Claims details = extractDetails(generatedToken);
		return details.getSubject();
	}
	
	public Date extractExpiredDate(String tokenGerado) {
		Claims details = extractDetails(tokenGerado);
		return details.getExpiration();
	}
	
	public boolean isExpired(String generatedToken) {
		Date expiredDate = extractExpiredDate(generatedToken);
		return expiredDate.before(new Date());
	}
	
	public boolean isValid(String generatedToken, UserDetails credential) {
		String login = extractEmail(generatedToken);
		boolean isValidLogin = login.equals(credential.getUsername());
		boolean isValid = !isExpired(generatedToken);
		return isValidLogin && isValid;
	}
	
	private Key getAssignatureKey() {
		byte[] keyByte = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	private Claims extractDetails(String generatedToken) {
		return Jwts.parserBuilder()
				.setSigningKey(getAssignatureKey())
				.build()
				.parseClaimsJws(generatedToken)
				.getBody();
	}
}
