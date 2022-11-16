package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserBl;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.UpdatePasswordDto;


import bo.edu.ucb.barkibu.dto.UpdateUserDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/user")
public class UserApi {
    private final UserBl userBl;

    public UserApi(UserBl userBl) {
        this.userBl = userBl;
    }

    // Actualiza la contraseña de un usuario
    @PutMapping("/password")
    public ResponseEntity<ResponseDto<String>> updatePassword(@RequestHeader Map<String,String> headers, @RequestBody UpdatePasswordDto updatePasswordDto) {
        if (updatePasswordDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String userName = AuthUtil.getUserNameFromToken(jwt);
                userBl.updatePassword(userName, updatePasswordDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Password Updated", "SCTY-0000", null);
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
    @PutMapping("/update")
    public ResponseEntity<ResponseDto<String>> updateUser(@RequestHeader Map<String,String> headers, @RequestBody UpdateUserDto updateUserDto) {
        if (updateUserDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String userName = AuthUtil.getUserNameFromToken(jwt);
                userBl.updateUser(userName, updateUserDto);
                ResponseDto<String> responseDto = new ResponseDto<>("User Updated", "SCTY-0000", null);
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
    // Obtiene la lista de grupos de un usuario
    @GetMapping("/group")
    public ResponseEntity<ResponseDto> getGroups(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            ResponseDto<List<String>> responseDto = new ResponseDto<>(userBl.getGroups(userName), "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }


}
