package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.VeterinaryBl;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.VeterinaryDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/veterinary")
public class VeterinaryApi {
    VeterinaryBl veterinaryBl;


    // Obtiene el consultorio de un veterinario por su token
    @GetMapping()
    public ResponseEntity<ResponseDto> getVeterinaryByUserName(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinaryDto veterinaryDto = veterinaryBl.findVeterinaryByUserName(userName);
            ResponseDto<VeterinaryDto> responseDto = new ResponseDto<>(veterinaryDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene el consultorio de un veterinario por su nombre de usuario
    @GetMapping("/{userName}")
    public ResponseEntity<ResponseDto> getVeterinaryByUserName(@PathVariable String userName) {
        try {
            VeterinaryDto veterinaryDto = veterinaryBl.findVeterinaryByUserName(userName);
            ResponseDto<VeterinaryDto> responseDto = new ResponseDto<>(veterinaryDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Crear un nuevo consultorio
    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createVeterinary(@RequestHeader Map<String,String> headers, @RequestBody VeterinaryDto veterinaryDto) {
        if (veterinaryDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "EDITAR INFORMACION DE CLINICA VETERINARIA");
                String userName = AuthUtil.getUserNameFromToken(jwt);
                veterinaryBl.createVeterinary(userName, veterinaryDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Veterinary Created", "SCTY-0000", null);
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

    // Edita el consultorio de un veterinario por su token
    @PutMapping()
    public ResponseEntity<ResponseDto<String>> updateVeterinary(@RequestHeader Map<String,String> headers, @RequestBody VeterinaryDto veterinaryDto) {
        if (veterinaryDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "EDITAR INFORMACION DE CLINICA VETERINARIA");
                String userName = AuthUtil.getUserNameFromToken(jwt);
                veterinaryBl.updateVeterinary(userName, veterinaryDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Veterinary Updated", "SCTY-0000", null);
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
