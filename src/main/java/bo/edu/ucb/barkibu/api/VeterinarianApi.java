package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.VeterinarianBl;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.HelpedPet;
import bo.edu.ucb.barkibu.entity.Reputation;
import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import bo.edu.ucb.barkibu.entity.VeterinarianRanking;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/veterinarian")
public class VeterinarianApi {
    private final VeterinarianBl veterinarianBl;

    VeterinarianApi(VeterinarianBl veterinarianBl) {
        this.veterinarianBl = veterinarianBl;
    }

    // Obtener información de un veterinario por su token
    @GetMapping()
    public ResponseEntity<ResponseDto> getVeterinarianUser(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinarianInfoDto user = veterinarianBl.findUserVeterinarianByUserName(userName);
            ResponseDto<VeterinarianInfoDto> responseDto = new ResponseDto<>(user, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtener información de un veterinario por su nombre de usuario
    @GetMapping("/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianUserByUserName(@PathVariable String userName) {
        try {
            VeterinarianInfoDto user = veterinarianBl.findUserVeterinarianByUserName(userName);
            ResponseDto<VeterinarianInfoDto> responseDto = new ResponseDto<>(user, "SCTY-0000", null);
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
            VeterinarianRanking ranking = veterinarianBl.findVeterinarianRankingByUserName(userName);
            ResponseDto<VeterinarianRanking> responseDto = new ResponseDto<>(ranking, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene el ranking de veterinario por su nombre de usuario
    @GetMapping("/ranking/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianRankingByUserName(@PathVariable String userName) {
        try {
            VeterinarianRanking ranking = veterinarianBl.findVeterinarianRankingByUserName(userName);
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
            Reputation reputation = veterinarianBl.findReputationByUserName(userName);
            ResponseDto<Reputation> responseDto = new ResponseDto<>(reputation, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene la reputación de un veterinario por su nombre de usuario
    @GetMapping("/reputation/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianReputationByUserName(@PathVariable String userName) {
        try {
            Reputation reputation = veterinarianBl.findReputationByUserName(userName);
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
            List<HelpedPet> contributions = veterinarianBl.findHelpedPetByUserName(userName);
            ResponseDto<List<HelpedPet>> responseDto = new ResponseDto<>(contributions, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene la lista de mascotas ayudadas por un veterinario por su nombre de usuario
    @GetMapping("/contribution/{userName}")
    public ResponseEntity<ResponseDto> getVeterinarianContributionByUserName(@PathVariable String userName) {
        try {
            List<HelpedPet> contributions = veterinarianBl.findHelpedPetByUserName(userName);
            ResponseDto<List<HelpedPet>> responseDto = new ResponseDto<>(contributions, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Lista de respuestas de un usuario por su token
    @GetMapping("/answers")
    public ResponseEntity<ResponseDto<List<VeterinarianAnswer>>> getAnswers(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            List<VeterinarianAnswer> answerDtos = veterinarianBl.getVeterinarianAnswers(userName);
            ResponseDto<List<VeterinarianAnswer>> responseDto = new ResponseDto<>(answerDtos, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<List<VeterinarianAnswer>> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }










/*
    //Test
    @GetMapping
    public UserDto test() {
        return new UserDto(1, 1, "Juan", "Perez", "test@ucb.edu.bo", "jperez","test", "test");
    }

    //Test get user by id
    @GetMapping("/{userId}")
    public UserDto getUserByPk(@PathVariable(name = "userId") Integer userId) {
        return securityBl.getUserByPk(userId);
    }

    //Get user by username
    @GetMapping("/")
    public ResponseDto<> getUserByUsername(@RequestHeader Map<String,String> headers) {
        String username = AuthUtil.isUserAuthenticated(AuthUtil.getTokenFromHeader(headers));
        return securityBl.findByUsername(username);
    }*/
}
