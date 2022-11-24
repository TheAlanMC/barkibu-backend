package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserPetOwnerBl;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.UserDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/user/pet-owner")
public class UserPetOwnerApi {
    private final UserPetOwnerBl userPetOwnerBl;

    public UserPetOwnerApi(UserPetOwnerBl userPetOwnerBl) {
        this.userPetOwnerBl = userPetOwnerBl;
    }

    // Registrar usuario dueño de mascota
    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createPetOwnerUser(@RequestBody CreateUserDto createUserDto) {
        if (createUserDto.validate()) {
            try {
                userPetOwnerBl.createPetOwnerUser(createUserDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Pet Owner User Created", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        }
        else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }

    // Actualiza los datos de un usuario
    @PutMapping()
    public ResponseEntity<ResponseDto<String>> updatePetOwnerUser(@RequestHeader Map<String,String> headers, @RequestBody UserDto userDto) {
        if (userDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String userName = AuthUtil.getUserNameFromToken(jwt);
                userPetOwnerBl.updatePetOwnerUser(userName, userDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Pet Owner User Updated", "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        }
        else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }

    // Obtiene los datos de un usuario

    @GetMapping()
    public ResponseEntity<ResponseDto<UserDto>> getPetOwnerUser(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            UserDto userDto = userPetOwnerBl.getPetOwnerUser(userName);
            ResponseDto<UserDto> responseDto = new ResponseDto<>(userDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<UserDto> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }


}
