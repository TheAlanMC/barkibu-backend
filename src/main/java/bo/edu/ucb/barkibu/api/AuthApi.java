package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.SecurityBl;
import bo.edu.ucb.barkibu.dto.AuthReqDto;
import bo.edu.ucb.barkibu.dto.AuthResDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.UserDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthApi {

    private SecurityBl securityBl;

    public AuthApi(SecurityBl securityBl) {
        this.securityBl = securityBl;
    }

    //Autentication
    @PostMapping()
    public ResponseDto<AuthResDto> authentication(@RequestBody AuthReqDto authReqDto) {
        if (authReqDto != null && authReqDto.getUserName() != null && authReqDto.getPassword() != null
            && !authReqDto.getUserName().isEmpty() && !authReqDto.getPassword().isEmpty()) {
            return new ResponseDto<>(securityBl.authenticate(authReqDto),"SCTY-0000", null);
        }
        else {
            return new ResponseDto<>(null,"SCTY-0001", "Invalid data");
        }
    }
    //Ejemplo modificacion de codigo de error
    //TODO: delete this method
    //No mezclar codigos de error y una bandera de success
    @RequestMapping("/test")
    public ResponseEntity<ResponseDto<AuthResDto>> test(@RequestBody AuthReqDto authReqDto) {
        if (authReqDto != null && authReqDto.getUserName() != null && authReqDto.getPassword() != null
            && !authReqDto.getUserName().isEmpty() && !authReqDto.getPassword().isEmpty()) {
            ResponseDto<AuthResDto> responseDto = new ResponseDto<>(securityBl.authenticate(authReqDto),"SCTY-0000", null);
            return ResponseEntity.ok(responseDto);
        }
        else {
            ResponseDto<AuthResDto> responseDto = new ResponseDto<>(null,"SCTY-0001", "Invalid data");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

}
