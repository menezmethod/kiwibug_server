package com.kb.kiwibugback.auth.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.kb.kiwibugback.auth.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${kiwibug.app.jwtSecret}")
  private String jwtSecret;

  @Value("${kiwibug.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(final Authentication authentication) {

    final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + this.jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, this.jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(final String token) {
    return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(final String authToken) {
    try {
      Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (final SignatureException e) {
      JwtUtils.logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (final MalformedJwtException e) {
      JwtUtils.logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (final ExpiredJwtException e) {
      JwtUtils.logger.error("JWT token is expired: {}", e.getMessage());
    } catch (final UnsupportedJwtException e) {
      JwtUtils.logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (final IllegalArgumentException e) {
      JwtUtils.logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }
}
