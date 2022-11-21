package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.SecurityBl;
import bo.edu.ucb.barkibu.dto.AuthReqDto;
import bo.edu.ucb.barkibu.dto.AuthResDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthApi {

    private final SecurityBl securityBl;

    public AuthApi(SecurityBl securityBl) {
        this.securityBl = securityBl;
    }

    // Autenticación de usuario
    @PostMapping()
    public ResponseEntity<ResponseDto> authentication(@RequestBody AuthReqDto authReqDto) {
        if (authReqDto.validate()) {
            try {
                ResponseDto<AuthResDto> responseDto = new ResponseDto<>(securityBl.authenticate(authReqDto), "SCTY-0000", null);
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } catch (BarkibuException e) {
                ResponseDto<AuthResDto> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
                return new ResponseEntity<>(responseDto, e.getHttpStatus());
            }
        } else {
            String statusCode = "SCTY-1001";
            ResponseDto<String> responseDto = new ResponseDto<>(null, statusCode, httpMessageUtilMap.get(statusCode).getMessage());
            return new ResponseEntity<>(responseDto, httpMessageUtilMap.get(statusCode).getHttpStatus());
        }
    }

    // refrescar token
    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseDto> refreshToken(@RequestHeader Map<String, String> headers) {
        try {
            String jwt = AuthUtil.getTokenFromHeader(headers);
            ResponseDto<AuthResDto> responseDto = new ResponseDto<>(securityBl.verifyRefreshToken(jwt), "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<AuthResDto> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }
}
