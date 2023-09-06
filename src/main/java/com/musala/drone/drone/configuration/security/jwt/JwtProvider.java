package com.musala.drone.drone.configuration.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${app.security.jwtExpiration}")
    private int jwtExpiration;


    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date create = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(create);
        calendar.add(Calendar.MINUTE, jwtExpiration);
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(create)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

}
