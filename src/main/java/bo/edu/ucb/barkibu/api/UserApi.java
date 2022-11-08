package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserBl;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {
    private UserBl userBl;

    public UserApi(UserBl userBl) {
        this.userBl = userBl;
    }

    @PostMapping
    public Map<String,String> createUser(@RequestBody  CreateUserDto createUserDto) {
        System.out.println(createUserDto);
        userBl.createUser(createUserDto);
        return Map.of("message", "User created");
    }
}
