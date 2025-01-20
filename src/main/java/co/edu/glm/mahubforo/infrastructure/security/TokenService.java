package co.edu.glm.mahubforo.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import co.edu.glm.mahubforo.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    // Mtodo para generar un token JWT
    public String generarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Build y Signing token JWT

            String token= JWT.create()
                    .withIssuer("foro hub")                     // Identificador del emisor del token
                    .withSubject(user.getCorreoElectronico())   // Usuario que se está autenticando
                    .withClaim("id", user.getId())        // Agregar un campo personalizado (ID del usuario)
                    .withIssuedAt(Date.from(Instant.now()))     // Campo `issuedAt` en UTC .withExpiresAt(Date.from(generarFechaExpiracion())) // Fecha de expiración del token
                    .withExpiresAt(Date.from(generarFechaExpiracion()))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error creating token JWT", e);
        }
    }

    // Mtodo para extraer el subject email del token
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("El token no puede ser nulo");
        }
        try {

            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Verified & decode token
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .acceptLeeway(10)
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (TokenExpiredException e) {
            throw new RuntimeException("Token Expired", e);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error Token Verification", e);
        }
    }

    //Expire Token Method
    private Instant generarFechaExpiracion() {
        Instant expiration = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
        return expiration;
    }
}
