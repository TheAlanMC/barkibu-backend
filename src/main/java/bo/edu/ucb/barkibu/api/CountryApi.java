package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.CountryBl;
import bo.edu.ucb.barkibu.dto.CountryDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
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
@RequestMapping("/v1/api/country")
public class CountryApi {

    private final CountryBl countryBl;

    public CountryApi(CountryBl countryBl) {
        this.countryBl = countryBl;
    }
    @GetMapping()
    public ResponseEntity<ResponseDto> getCountries(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            List<CountryDto> countryDtos = countryBl.findAllCountries();
            ResponseDto<List<CountryDto>> responseDto = new ResponseDto<>(countryDtos, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }
}
