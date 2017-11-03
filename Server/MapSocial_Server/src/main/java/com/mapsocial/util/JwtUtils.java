package com.mapsocial.util;

import com.mapsocial.constant.JwtConstants;
import com.mapsocial.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created by yue.gan on 2017/11/3.
 */
public class JwtUtils {


    private static final long EXPIRATION =  24 * 60 * 60 * 1000;

    public static String createToken (String subject) {
        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JwtConstants.TOKEN_SECRET_KEY)
                .compact();
        return JwtConstants.TOKEN_HEADER_PARAM_HEAD + token;
    }

    public static Jws<Claims> parseToken (String token) {
        Jws<Claims> result = Jwts.parser()
                .setSigningKey(JwtConstants.TOKEN_SECRET_KEY)
                .parseClaimsJws(token.replace(JwtConstants.TOKEN_HEADER_PARAM_HEAD, ""));
        return result;
    }
}
