package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.VeterinarianInfoBl;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/veterinarian")
public class VeterinarianInfoApi {
    private final VeterinarianInfoBl veterinarianInfoBl;

    VeterinarianInfoApi(VeterinarianInfoBl veterinarianInfoBl) {
        this.veterinarianInfoBl = veterinarianInfoBl;
    }

    // Obtener información de un veterinario por su token
    @GetMapping()
    public ResponseEntity<ResponseDto> getVeterinarianUser(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinarianInfo user = veterinarianInfoBl.findUserVeterinarianByUserName(userName);
            ResponseDto<VeterinarianInfo> responseDto = new ResponseDto<>(user, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtener información de un veterinario por su nombre de usuario
    @GetMapping("/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianUserByUserName(@RequestHeader Map<String,String> headers, @PathVariable String userName) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            VeterinarianInfo user = veterinarianInfoBl.findUserVeterinarianByUserName(userName);
            ResponseDto<VeterinarianInfo> responseDto = new ResponseDto<>(user, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene el ranking de veterinario por su token
    @GetMapping("/ranking")
    public ResponseEntity<ResponseDto> getVeterinarianRanking(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinarianRanking ranking = veterinarianInfoBl.findVeterinarianRankingByUserName(userName);
            ResponseDto<VeterinarianRanking> responseDto = new ResponseDto<>(ranking, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene el ranking de veterinario por su nombre de usuario
    @GetMapping("/ranking/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianRankingByUserName(@RequestHeader Map<String,String> headers, @PathVariable String userName) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            VeterinarianRanking ranking = veterinarianInfoBl.findVeterinarianRankingByUserName(userName);
            ResponseDto<VeterinarianRanking> responseDto = new ResponseDto<>(ranking, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene la reputación de un veterinario por su token
    @GetMapping("/reputation")
    public ResponseEntity<ResponseDto> getVeterinarianReputation(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            Reputation reputation = veterinarianInfoBl.findReputationByUserName(userName);
            ResponseDto<Reputation> responseDto = new ResponseDto<>(reputation, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene la reputación de un veterinario por su nombre de usuario
    @GetMapping("/reputation/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianReputationByUserName(@RequestHeader Map<String,String> headers, @PathVariable String userName) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            Reputation reputation = veterinarianInfoBl.findReputationByUserName(userName);
            ResponseDto<Reputation> responseDto = new ResponseDto<>(reputation, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene la lista de mascotas ayudadas por un veterinario por su token
    @GetMapping("/contribution")
    public ResponseEntity<ResponseDto> getVeterinarianContribution(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            List<HelpedPet> contributions = veterinarianInfoBl.findHelpedPetByUserName(userName);
            ResponseDto<List<HelpedPet>> responseDto = new ResponseDto<>(contributions, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene la lista de mascotas ayudadas por un veterinario por su nombre de usuario
    @GetMapping("/contribution/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianContributionByUserName(@RequestHeader Map<String,String> headers, @PathVariable String userName) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.getUserNameFromToken(jwt);
            List<HelpedPet> contributions = veterinarianInfoBl.findHelpedPetByUserName(userName);
            ResponseDto<List<HelpedPet>> responseDto = new ResponseDto<>(contributions, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Lista de respuestas de un usuario por su token
    @GetMapping("/answers")
    public ResponseEntity<ResponseDto> getAnswers(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            List<VeterinarianOwnAnswer> veterinarianOwnAnswers = veterinarianInfoBl.getVeterinarianAnswers(userName);
            ResponseDto<List<VeterinarianOwnAnswer>> responseDto = new ResponseDto<>(veterinarianOwnAnswers, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }
}
