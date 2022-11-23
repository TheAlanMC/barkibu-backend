package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.BreedBl;
import bo.edu.ucb.barkibu.dto.BreedDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/breed")
public class BreedApi {

    BreedBl breedBl;

    public BreedApi(BreedBl breedBl) {
        this.breedBl = breedBl;
    }
    //todo: maybe only get all breeds
    @GetMapping()
    public ResponseEntity<ResponseDto> getBreeds(@RequestHeader Map<String, String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            List<BreedDto> breedDtos = breedBl.findAllBreeds();
            ResponseDto<List<BreedDto>> responseDto = new ResponseDto<>(breedDtos, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

}
