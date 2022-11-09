package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserBl;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/user")
public class UserApi {
    private UserBl userBl;

    public UserApi(UserBl userBl) {
        this.userBl = userBl;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<String>> createUser(@RequestHeader Map<String,String> headers, @RequestBody  CreateUserDto createUserDto) {
        try{
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.verifyHasRole(jwt,"CREAR USUARIO");
            userBl.createUser(createUserDto);
            ResponseDto<String> responseDto = new ResponseDto<>("User Created", "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        catch(BarkibuException e){
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
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
