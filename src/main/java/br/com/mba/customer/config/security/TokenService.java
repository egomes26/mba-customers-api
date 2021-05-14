package br.com.mba.customer.config.security;

import br.com.mba.customer.documents.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${auth.jwt.expiration}")
    private String expiration;

    @Value("${auth.jwt.secret}")
    private String secret;

    public String buildToken(Authentication authentication) {
        User loged = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime()+ Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API beaute")
                .setSubject(loged.getUuid().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UUID getUUIDUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return UUID.fromString(claims.getSubject());
    }
}
