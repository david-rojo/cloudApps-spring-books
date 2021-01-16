package com.cloudapps.springbooks.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.dtos.responses.WhoamiResponseDto;
import com.cloudapps.springbooks.security.Constants;
import com.cloudapps.springbooks.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {

	private Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

	public WhoamiResponseDto getUser(String authHeader) {
		String jwt = this.getJwt(authHeader);
		Optional<Claims> claims = this.decodeJWT(jwt);
		if (!claims.isEmpty()) {
			return WhoamiResponseDto.builder()
					.username(claims.get().getSubject())
					.build();
		}
		return WhoamiResponseDto.builder()
				.username("user not detected in JWT")
				.build();
	}
	
	
	public boolean isValid(String authHeader) {
		String jwt = this.getJwt(authHeader);
		if (!jwt.equalsIgnoreCase("")) {
			Optional<Claims> claims = this.decodeJWT(jwt);
			if (!claims.isEmpty()) {
				//check that token is not expired
				boolean valid = LocalDateTime.now()
						.isBefore(LocalDateTime.ofInstant(claims.get().getExpiration().toInstant(), ZoneId.systemDefault()));
				if (valid) {
					log.info("Valid JWT");
				}
				else {
					log.info("JWT expired");
				}
				return valid;
			}
			else {
				log.info("JWT cannot be decoded");
			}
		}
		else {
			log.info("JWT cannot be retrieved from headers");
		}
		return false;		
	}

	private Optional<Claims> decodeJWT(String jwt) {
		try {
			//This line will throw an exception if it is not a signed JWS (as expected)
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(Constants.SUPER_SECRET_KEY))
					.parseClaimsJws(jwt).getBody();
			return Optional.ofNullable(claims);
		}
		catch (Exception e) {
			log.error("Error decoding JWT: {}", e.getLocalizedMessage());
			return Optional.empty();
		}
	}	

	private String getJwt(String authHeader) {
		return authHeader.substring(
				Constants.TOKEN_BEARER_PREFIX.length(),
				authHeader.length());
	}

}