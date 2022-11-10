package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.PetBl;
import bo.edu.ucb.barkibu.dto.CreatePetDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/pet")
public class PetApi {
    private PetBl petBl;

    public PetApi(PetBl petBl) {
        this.petBl = petBl;
    }

    //Registar nueva mascota
    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createPet(@RequestHeader Map<String,String> headers, @RequestBody CreatePetDto createPetDto) {
        if (createPetDto.validate()){
            try {
                //TODO: VALIDAR QUE EL USUARIO ESTE AUTENTICADO
                // Verificamos que el usuario este autenticado
                //String jwt = AuthUtil.getTokenFromHeader(headers);
                //AuthUtil.verifyHasRole(jwt, "CREAR USUARIO");
                petBl.createPet(createPetDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Pet Created", "SCTY-0000", null);
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

}
