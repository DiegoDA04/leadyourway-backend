package pe.upc.authservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.upc.authservice.domain.entities.User;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "oniichanthatwasenoughceahnusahunstehausntahnusaheunsathuanstehua";

    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

}
