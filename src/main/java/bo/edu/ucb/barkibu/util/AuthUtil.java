package bo.edu.ucb.barkibu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

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
            throw new BarkibuException("No token provided");
        }
        if (headers.get("Authorization") != null) {
            jwt = headers.get("Authorization").split(" ")[1];
        }
        else {
            jwt = headers.get("authorization").split(" ")[1];
        }
        return jwt;
    }

    //Verificamos si el token es valido
    public static void verifyHasRole(String jwt, String role) {
        List<String> roles = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .build()
                .verify(jwt)
                .getClaim("roles")
                .asList(String.class);
        if (!roles.contains(role)) {
            //TODO: FIX THROWING EXCEPTION
            throw new BarkibuException("User does not have permission to perform this action");
        }
    }
}

