package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserBl;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.ResponseDto;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/pet-owner")
public class PetOwnerApi {
    UserBl userBl;

    public PetOwnerApi(UserBl userBl) {
        this.userBl = userBl;
    }

    // Registrar usuario dueño de mascota
    @PostMapping("/pet-owner")
    public ResponseEntity<ResponseDto<String>> createPetOwnerUser(@RequestBody CreateUserDto createUserDto) {
        if (createUserDto.validate()) {
            try {
                userBl.createPetOwnerUser(createUserDto);
                ResponseDto<String> responseDto = new ResponseDto<>("Pet Owner User Created", "SCTY-0000", null);
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
