package bo.edu.ucb.barkibu.api;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@RestController
@RequestMapping(path="/v1/api/security")
public class SecurityApi {

    @PostMapping (path="/username")
    public Map<String,String> validateUsername( @RequestBody  Map<String,String> body){
        Map<String,String> result = new HashMap<>();
        result.put("statusCode","SCTY-0000");
        result.put("result","OK");
        result.put("errorDetail",null);
        return result;
    };

    @PostMapping (path="/auth")
    Map<String,Object>  validateAuth( @RequestBody  Map<String,String> body){
        Map<String,Object> result = new HashMap<>();
        Map<String,String> response= new HashMap<>();

        if (body.get("username").equals("jperez") && body.get("password").equals("123456")) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                String token = JWT.create()
                        .withSubject("100")
                        .withClaim("name", "Juan Perez")
                        .withIssuedAt(java.util.Date.from(java.time.Instant.now()))
                        .withExpiresAt(java.util.Date.from(java.time.Instant.now().plusSeconds(360)))
                        .sign(algorithm);
                String refreshToken = JWT.create()
                        .withSubject("100")
                        .withClaim("name", "Juan Perez")
                        .withIssuedAt(java.util.Date.from(java.time.Instant.now()))
                        .withExpiresAt(java.util.Date.from(java.time.Instant.now().plusSeconds(490)))
                        .sign(algorithm);    

                response.put("token", token);
                response.put("refreshToken", refreshToken);
                
                result.put("statusCode","SCTY-0000");
                result.put("result",response);
                result.put("errorDetail",null);
            } catch (JWTCreationException exception){
                result.put("statusCode","SCTY-0001");
                result.put("result","ERROR");
                result.put("errorDetail","Invalid Signing configuration / Couldn't convert Claims.");
            }
        } else {
            result.put("statusCode","SCTY-0001");
            result.put("result","ERROR");
            result.put("errorDetail","Invalid username or password.");
        }
        
        return result;
    };

}

