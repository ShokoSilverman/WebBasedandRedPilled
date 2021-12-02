package com.websters.webbasedandredpilled.Models;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Map;
@RequiredArgsConstructor
public class JWTPOJO {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    static String secret = "jiouresdxfvbnjhgdznjhdz jbuox oih nih897iydlv ipm fktdjcvh;drsxcvb";

    @Getter
    private final String token;
    @Getter
    private final String username;

    @Getter
    private final Date issuedAt, expiration;
    private final Claims claims;


    public static JWTPOJO parseToken(String token) {
        Claims claims = JWTPOJO.getAllClaims(token);
        JWTPOJO jwt = new JWTPOJO(token, claims.getSubject(), claims.getIssuedAt(), claims.getExpiration(),
                claims);
        return jwt;
    }

    public static Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseClaimsJws(token).getBody();
    }

    public static JWTPOJO from(String username, Map<String, Object> claims) {
        String token = build(username, claims);
        return parseToken(token);
    }

    private static String build(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .compact();
    }

}
