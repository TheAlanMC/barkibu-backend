package bo.edu.ucb.barkibu.api;

import bo.edu.ucb.barkibu.bl.UserBl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/pet-owner")
public class PetOwnerApi {
    UserBl userBl;

    public PetOwnerApi(UserBl userBl) {
        this.userBl = userBl;
    }

}
