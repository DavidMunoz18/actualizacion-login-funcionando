package edu.proyectoApi.servicios;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    // Generar una clave secreta segura y de tamaño adecuado para HS256
    private SecretKey getSigningKey() {
        // Genera una clave secreta con un tamaño adecuado para HMAC-SHA-256
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Método para generar el token
    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de expiración
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Método para validar el token
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // El token no es válido
            return false;
        }
    }

    // Método para obtener el usuario del token
    public String obtenerUsuarioDelToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}