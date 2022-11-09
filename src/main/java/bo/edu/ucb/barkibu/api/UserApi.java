package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.SecurityBl;
import bo.edu.ucb.barkibu.bl.UserBl;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {
    private UserBl userBl;
    private SecurityBl securityBl;

    public UserApi(UserBl userBl, SecurityBl securityBl) {
        this.userBl = userBl;
        this.securityBl = securityBl;
    }

    @PostMapping
    public Map<String,String> createUser(@RequestHeader Map<String,String> headers, @RequestBody  CreateUserDto createUserDto) {
        if (headers.get("Authorization") == null && headers.get("authorization") == null) {
            return Map.of("message", "Unauthorized");
        }
        String jwt ="";
        if (headers.get("Authorization") != null) {
            jwt = headers.get("Authorization").split(" ")[1];
        }
        else {
            jwt = headers.get("authorization").split(" ")[1];
        }
        if(this.securityBl.tokenHasRole(jwt,"CREAR USUARIO")) {
            userBl.createUser(createUserDto);
            return Map.of("message", "User created");
        } else {
            return Map.of("message", "Unauthorized");
        }
    }
}
