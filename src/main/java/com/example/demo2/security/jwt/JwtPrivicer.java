package com.example.demo2.security.jwt;

import com.example.demo2.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtPrivicer {
    public static final Logger logger = LoggerFactory.getLogger(JwtPrivicer.class);
    private String jwtSecret = "quyenpham1997";
    private int jwtExpiration = 86400;

    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invaild JWT signture -> Message :{}", e);
        } catch (MalformedJwtException e) {
            logger.error("Invaild format token -> Message :{}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expire jwt token -> Message :{}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupport Jwt token -> Message :{}", e);
        } catch (IllegalArgumentException e) {
            logger.error("Jwt claims string is empty -> Message :{}", e);
        }
        return false;
    }
    public String getUsernameFromToken(String token){
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
        return  username;
    }
}
