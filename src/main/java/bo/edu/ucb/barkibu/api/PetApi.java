package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.PetBl;
import bo.edu.ucb.barkibu.dto.CreatePetDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
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
    private PetBl petBl;

    public PetApi(PetBl petBl) {
        this.petBl = petBl;
    }
    // TODO: CREATE BY USERNAME?

    //Registar nueva mascota
    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createPet(@RequestHeader Map<String,String> headers, @RequestBody CreatePetDto createPetDto) {
        if (createPetDto.validate()){
            try {
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "REGISTRAR MASCOTA");
                petBl.createPet(createPetDto);
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

}
