package com.bar.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bar.barproject.Model.admin;
import com.bar.barproject.Model.waiter;

import java.util.Date;
import java.util.UUID;


@Component
public class TokenUtil {
   

    private final String secret = "cccccccc";
    // Replace with your own secret key

    public String generateTokenadmin(admin username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // Token validity for 24 hours

        return Jwts.builder()
                .setSubject(username.getUser())
                .setIssuedAt(now)
                .claim("Role", "admin")
                .claim("password", username.getPassword())
                .claim("id",1)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public String generateTokenwaiter(waiter username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // Token validity for 24 hours

        return Jwts.builder()
                .setSubject(username.getName())
                .setIssuedAt(now)
                .claim("Role", "waiter")
                .claim("password", username.getPassword())
                .claim("id",username.getId())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public  boolean isValidToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            // Additional validation logic (e.g., expiration, custom claims) can be added here
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getRoleFromToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Claims body = claims.getBody();
            String role = (String) body.get("Role");
            return role;
        } catch (Exception e) {
            return null;
        }
    }
    public String getIdFromToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Claims body = claims.getBody();
            String id =  body.get("id").toString();
            return id;
        } catch (Exception e) {

            return null;
        }
    }public String getNameFromToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Claims body = claims.getBody();
            String id =  body.get("sub").toString();
            return id;
        } catch (Exception e) {

            return null;
        }
    }
}
