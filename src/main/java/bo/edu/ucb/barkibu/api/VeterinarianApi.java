package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserBl;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.HelpedPet;
import bo.edu.ucb.barkibu.entity.Reputation;
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
    private UserBl userBl;

    public VeterinarianApi(UserBl userBl) {
        this.userBl = userBl;
    }

    //Registar usuario veterinario
    @PostMapping("/")
    public ResponseEntity<ResponseDto<String>> createVeterinarianUser(@RequestHeader Map<String,String> headers, @RequestBody  CreateUserDto createUserDto) {
        if (createUserDto.validate()) {
            try {
                // Verificamos que el usuario este autenticado
                String jwt = AuthUtil.getTokenFromHeader(headers);
                AuthUtil.verifyHasRole(jwt, "REGISTRAR USUARIO VETERINARIO");
                userBl.createVeterinarianUser(createUserDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Veterinary User Created", "SCTY-0000", null);
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



    // Obtener información de un veterinario por su token
    @GetMapping("/")
    public ResponseEntity<ResponseDto> getVeterinarianUser(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinarianDto user = userBl.findUserVeterinarianByUserName(userName);
            ResponseDto<VeterinarianDto> responseDto = new ResponseDto<>(user, "SCTY-0000", null);
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
            VeterinarianDto user = userBl.findUserVeterinarianByUserName(userName);
            ResponseDto<VeterinarianDto> responseDto = new ResponseDto<>(user, "SCTY-0000", null);
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
            VeterinarianRankingDto ranking = userBl.findVeterinarianRankingByUserName(userName);
            ResponseDto<VeterinarianRankingDto> responseDto = new ResponseDto<>(ranking, "SCTY-0000", null);
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
            VeterinarianRankingDto ranking = userBl.findVeterinarianRankingByUserName(userName);
            ResponseDto<VeterinarianRankingDto> responseDto = new ResponseDto<>(ranking, "SCTY-0000", null);
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
            Reputation reputation = userBl.findReputationByUserName(userName);
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
            Reputation reputation = userBl.findReputationByUserName(userName);
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
            List<HelpedPet> contributions = userBl.findHelpedPetByUserName(userName);
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
            List<HelpedPet> contributions = userBl.findHelpedPetByUserName(userName);
            ResponseDto<List<HelpedPet>> responseDto = new ResponseDto<>(contributions, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene el consultorio de un veterinario por su token
    @GetMapping("/veterinary")
    public ResponseEntity<ResponseDto> getVeterinaryByUserName(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinaryDto veterinaryDto = userBl.findVeterinaryByUserName(userName);
            ResponseDto<VeterinaryDto> responseDto = new ResponseDto<>(veterinaryDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene el consultorio de un veterinario por su nombre de usuario
    @GetMapping("/veterinary/{userName}")
    public ResponseEntity<ResponseDto> getVeterinaryByUserName(@PathVariable String userName) {
        try {
            VeterinaryDto veterinaryDto = userBl.findVeterinaryByUserName(userName);
            ResponseDto<VeterinaryDto> responseDto = new ResponseDto<>(veterinaryDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Obtiene el perfil de un veterinario por su token con el fin de editar su perfil
    @GetMapping("/profile")
    public ResponseEntity<ResponseDto> getVeterinarianProfile(@RequestHeader Map<String,String> headers) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.verifyHasRole(jwt, "EDITAR INFORMACION DE VETERINARIO");
            String userName = AuthUtil.getUserNameFromToken(jwt);
            VeterinarianProfileDto veterinarianProfileDto = userBl.getVeterinarianProfile(userName);
            ResponseDto<VeterinarianProfileDto> responseDto = new ResponseDto<>(veterinarianProfileDto, "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
            return new ResponseEntity<>(responseDto, e.getHttpStatus());
        }
    }

    // Edita el perfil de un veterinario por su token
    @PostMapping("/profile")
    public ResponseEntity<ResponseDto> updateVeterinarianProfile(@RequestHeader Map<String,String> headers, @RequestBody VeterinarianProfileDto veterinarianProfileDto) {
        try {
            // Verificamos que el usuario este autenticado
            String jwt = AuthUtil.getTokenFromHeader(headers);
            AuthUtil.verifyHasRole(jwt, "EDITAR INFORMACION DE VETERINARIO");
            String userName = AuthUtil.getUserNameFromToken(jwt);
            userBl.updateVeterinarianProfile(userName, veterinarianProfileDto);
            ResponseDto<String> responseDto = new ResponseDto<>("Profile Updated Successfully", "SCTY-0000", null);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (BarkibuException e) {
            ResponseDto<String> responseDto = new ResponseDto<>(null, e.getStatusCode(), e.getMessage());
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
