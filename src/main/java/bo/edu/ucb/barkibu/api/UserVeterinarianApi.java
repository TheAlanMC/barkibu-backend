package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserVeterinarianBl;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.entity.VeterinarianUser;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/user/veterinarian")
public class UserVeterinarianApi {

    private final UserVeterinarianBl userVeterinarianBl;

    UserVeterinarianApi(UserVeterinarianBl userVeterinarianBl) {
        this.userVeterinarianBl = userVeterinarianBl;
    }

    //Registar usuario veterinario
    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createVeterinarianUser(@RequestHeader Map<String,String> headers, @RequestBody CreateUserDto createUserDto) {
        if (createUserDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "REGISTRAR USUARIO VETERINARIO");
                userVeterinarianBl.createVeterinarianUser(createUserDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Veterinarian User Created", "SCTY-0000", null);
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

    // Obtiene el perfil de un usuario veterinario por su token con el fin de editar su perfil
    @GetMapping()
    public ResponseEntity<ResponseDto> getVeterinarianProfile(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.verifyHasRole(jwt, "EDITAR INFORMACION DE VETERINARIO");
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinarianUser veterinarianUser = userVeterinarianBl.getVeterinarianUser(userName);
            ResponseDto<VeterinarianUser> responseDto = new ResponseDto<>(veterinarianUser, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Edita el perfil de un usuario veterinario por su token
    @PutMapping()
    public ResponseEntity<ResponseDto<String>> updateVeterinarianProfile(@RequestHeader Map<String,String> headers, @RequestBody VeterinarianUser veterinarianUser) {
        if (veterinarianUser.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "EDITAR INFORMACION DE VETERINARIO");
                String userName = AuthUtil.getUserNameFromToken(jwt);
                userVeterinarianBl.updateVeterinarianProfile(userName, veterinarianUser);
                ResponseDto<String> responseDto = new ResponseDto<>("Profile Updated", "SCTY-0000", null);
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
}
