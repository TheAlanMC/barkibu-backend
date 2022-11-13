package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.PetTreatmentBl;
import bo.edu.ucb.barkibu.dto.PetTreatmentDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;

@RestController
@RequestMapping("/v1/api/pet/treatment")
public class PetTreatmentApi {
    PetTreatmentBl petTreatmentBl;

    public PetTreatmentApi(PetTreatmentBl petTreatmentBl) {
        this.petTreatmentBl = petTreatmentBl;
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<String>> createPetTreatment(@RequestHeader Map<String,String> headers, @RequestBody PetTreatmentDto petTreatmentDto) {
        if (petTreatmentDto.validate()){
            try {
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "REGISTRAR TRATAMIENTOS DE LA MASCOTA");
                petTreatmentBl.createPetTreatment(petTreatmentDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Treatment Created", "SCTY-0000", null);
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
