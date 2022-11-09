package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.SecurityBl;
import bo.edu.ucb.barkibu.dto.AuthReqDto;
import bo.edu.ucb.barkibu.dto.AuthResDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.UserDto;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

    private SecurityBl securityBl;

    public AuthApi(SecurityBl securityBl) {
        this.securityBl = securityBl;
    }

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

    //Autentication
    @PostMapping()
    public ResponseDto<AuthResDto> authentication(@RequestBody AuthReqDto authReqDto) {
        if (authReqDto != null && authReqDto.getUserName() != null && authReqDto.getPassword() != null) {
            return new ResponseDto<>(securityBl.authenticate(authReqDto),"SCTY-0000", null);
        }
        else {
            return new ResponseDto<>(null,"SCTY-0001", "Invalid data");
        }
    }
}
