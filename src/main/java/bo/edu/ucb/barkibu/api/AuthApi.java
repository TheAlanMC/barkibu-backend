package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.SecurityBl;
import bo.edu.ucb.barkibu.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

    private SecurityBl securityBl;

    public AuthApi(SecurityBl securityBl) {
        this.securityBl = securityBl;
    }

    @GetMapping
    public UserDto test() {
        return new UserDto(1, 1, "Juan", "Perez", "test@ucb.edu.bo", "test", "test");
    }

    @GetMapping("/{userId}")
    public UserDto getUserByPk(@PathVariable(name = "userId") Integer userId) {
        System.out.println("userId: " + userId);
        return securityBl.getUserByPk(userId);
    }
}
