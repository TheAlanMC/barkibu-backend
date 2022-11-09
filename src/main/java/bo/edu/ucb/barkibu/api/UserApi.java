package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.SecurityBl;
import bo.edu.ucb.barkibu.bl.UserBl;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.UserDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/user")
public class UserApi {
    private UserBl userBl;
    private SecurityBl securityBl;

    public UserApi(UserBl userBl, SecurityBl securityBl) {
        this.userBl = userBl;
    }

    @PostMapping
    public ResponseDto<String> createUser(@RequestHeader Map<String,String> headers, @RequestBody  CreateUserDto createUserDto) {
        try{
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.verifyHasRole(jwt,"CREAR USUARIO");
            userBl.createUser(createUserDto);
            return new ResponseDto<>("User Created", "SCTY-0000", null);
        }
        catch(BarkibuException e){
            //TODO: implement code to log exception
            return new ResponseDto<>(null, "SCTY-0001", e.getMessage());
        }
    }
/*
    //Test
    @GetMapping
    public UserDto test() {
        return new UserDto(1, 1, "Juan", "Perez", "test@ucb.edu.bo", "jperez","test", "test");
    }

    //Test get user by id
    @GetMapping("/{userId}")
    public UserDto getUserByPk(@PathVariable(name = "userId") Integer userId) {
        return securityBl.getUserByPk(userId);
    }

    //Get user by username
    @GetMapping("/")
    public ResponseDto<> getUserByUsername(@RequestHeader Map<String,String> headers) {
        String username = AuthUtil.isUserAuthenticated(AuthUtil.getTokenFromHeader(headers));
        return securityBl.findByUsername(username);
    }*/
}
