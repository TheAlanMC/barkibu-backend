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

    //Registar usuario veterinario
    @PostMapping("/veteriarian")
    public ResponseEntity<ResponseDto<String>> createVeterinarianUser(@RequestHeader Map<String,String> headers, @RequestBody  CreateUserDto createUserDto) {
        if (createUserDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "CREAR USUARIO");
                userBl.createVeterinarianUser(createUserDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Veterinary User Created", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        }
        else {
            ResponseDto<String> responseDto = new ResponseDto<>(null, "SCTY-1001", "At least one field is empty");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    // Registrar usuario dueño de mascota
    @PostMapping("/pet-owner")
    public ResponseEntity<ResponseDto<String>> createPetOwnerUser(@RequestBody  CreateUserDto createUserDto) {
        if (createUserDto.validate()) {
            try {
                userBl.createPetOwnerUser(createUserDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Pet Owner User Created", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        }
        else {
            ResponseDto<String> responseDto = new ResponseDto<>(null, "SCTY-1001", "At least one field is empty");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
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
