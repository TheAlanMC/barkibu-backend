package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.PetBl;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.PetInfo;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/pet")
public class PetApi {
     PetBl petBl;

    public PetApi(PetBl petBl) {
        this.petBl = petBl;
    }

    //Registrar nueva mascota
    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createPet(@RequestHeader Map<String,String> headers, @RequestBody CreatePetDto createPetDto) {
        if (createPetDto.validate()){
            try {
                String jwt = AuthUtil.getTokenFromHeader(headers);
                String userName = AuthUtil.getUserNameFromToken(jwt);
                AuthUtil.verifyHasRole(jwt, "REGISTRAR MASCOTA");
                petBl.createPet(userName, createPetDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Pet Created", "SCTY-0000", null);
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

    //Información de la mascota por su ID
    @GetMapping("/pet-info/{petId}")
    public ResponseEntity<ResponseDto> getPetInfo(@RequestHeader Map<String, String> headers, @PathVariable Integer petId) {

        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            PetInfo user = petBl.findPetInfoByPetId(petId);
            ResponseDto<PetInfo> responseDto = new ResponseDto<>(user, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }
    // Actualiza los datos de una mascota por id
    @PutMapping("/{pet_id}")
    public ResponseEntity<ResponseDto<String>> updatePet(@RequestHeader Map<String,String> headers, @RequestBody UpdatePetDto updatePetDto,@PathVariable Integer pet_id

                                                        ) {
        if (updatePetDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.getUserNameFromToken(jwt);
                petBl.updatePet(pet_id, updatePetDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Pet Updated", "SCTY-0000", null);
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
