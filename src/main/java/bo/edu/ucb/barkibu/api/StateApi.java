package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.CountryBl;
import bo.edu.ucb.barkibu.bl.StateBl;
import bo.edu.ucb.barkibu.dto.CountryDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.dto.StateDto;
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
@RequestMapping("/v1/api/state")
public class StateApi {

    private final StateBl stateBl;

    public StateApi(StateBl stateBl) {
        this.stateBl = stateBl;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto> getStates(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            List<StateDto> stateDtos = stateBl.findAllStates();
            ResponseDto<List<StateDto>> responseDto = new ResponseDto<>(stateDtos, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

}
