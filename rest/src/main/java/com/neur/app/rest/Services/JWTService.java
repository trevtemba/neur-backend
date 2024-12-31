package com.neur.app.rest.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private final String secretKey;

    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey generatedKey = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(generatedKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Claims are the pieces of data that make up the JSON payload, a hashmap is used due to its key: value mapping.
    // We want the user, token issue, and token expiration to be in the payload, so that is what we add.
    // We then sign the token, the alg in the header is determined by the signing algorithm: HmacSHA256 (hs256)
    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //If the subject (user) is a valid user and the token isn't expired, it's valid.
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Parses token to retrieve the username (subject)
    public String extractUserName(String token) {
        Claims claims = Jwts.parser()
                //Only configuration needed for this parser, build with this key configuration
                .verifyWith(getKey())
                .build()
                //Parses jwt string, if the signature is invalid or token is malformed, exception is thrown
                .parseSignedClaims(token)
                //Extracts payload (subject, issue, expiration)
                .getPayload();

        //Returning subject, which is user in this case.
        return claims.getSubject();
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Parses token to retrieve expiration date (expiration) (Logic is same as previous parse)
    private Date extractExpiration(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration();
    }
}
