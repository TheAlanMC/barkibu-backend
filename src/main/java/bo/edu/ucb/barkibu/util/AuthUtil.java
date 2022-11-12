package bo.edu.ucb.barkibu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class AuthUtil {
    public final static String JWT_SECRET = "barkibu";

    //Obtenemos el subject del token
    public static String isUserAuthenticated(String jwtToken) {
        String subject = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .build()
                .verify(jwtToken)
                .getSubject();
        return subject;
    }

    //Obtenemos el token del header
    public static String getTokenFromHeader(Map<String,String> headers) {
        String jwt ="";
        if (headers.get("Authorization") == null && headers.get("authorization") == null) {
            throw new BarkibuException("SCTY-2003", "No token provided", HttpStatus.UNAUTHORIZED);
        }
        if (headers.get("Authorization") != null) {
            jwt = headers.get("Authorization").split(" ")[1];
        }
        else {
            jwt = headers.get("authorization").split(" ")[1];
        }
        return jwt;
    }

    //Verificamos si el tiene el rol para ejecutar la accion
    public static void verifyHasRole(String jwt, String role) {
        try{List<String> roles = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .build()
                .verify(jwt)
                .getClaim("roles")
                .asList(String.class);
        if (!roles.contains(role)) {
            throw new BarkibuException("User does not have permission to access this resource", "SCTY-2001", HttpStatus.FORBIDDEN);
        }}
        catch (JWTVerificationException exception){
            throw new BarkibuException("Invalid token", "SCTY-2002", HttpStatus.UNAUTHORIZED);
        }
    }
}

