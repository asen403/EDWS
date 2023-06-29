package com.whs.edws.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtil {

    private static final String SALT = "edws";

    public static String parseToken(String token){
        Claims claims = Jwts.parser().setSigningKey(SALT).parseClaimsJws(token).getBody();
        return claims.getId();

    }

    public static String getToken(String id){
        JwtBuilder builder = Jwts.builder()
                .setId(id).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SALT);
        return builder.compact();
    }
}
