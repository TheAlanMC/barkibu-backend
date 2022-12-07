package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.SymptomBl;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.SymptomDto;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/symptom")
public class SymptomApi {

    private final SymptomBl symptomBl;

    public SymptomApi(SymptomBl symptomBl) {
        this.symptomBl = symptomBl;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getCategories(@RequestHeader Map<String, String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            List<SymptomDto> symptomDtos = symptomBl.findAllSymptom();
            ResponseDto<List<SymptomDto>> responseDto = new ResponseDto<>(symptomDtos, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }
}