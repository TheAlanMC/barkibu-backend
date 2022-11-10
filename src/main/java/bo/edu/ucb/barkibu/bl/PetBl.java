package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetDao;
import bo.edu.ucb.barkibu.dto.CreatePetDto;
import bo.edu.ucb.barkibu.entity.Pet;
import org.springframework.stereotype.Service;

@Service
public class PetBl {
    private PetDao petDao;

    public PetBl(PetDao petDao) {
        this.petDao = petDao;
    }

    public void createPet(CreatePetDto createPetDto) {
        Pet pet = new Pet();
        pet.setUserId(createPetDto.getUserId());
        pet.setBreedId(createPetDto.getBreedId());
        pet.setName(createPetDto.getName());
        pet.setGender(createPetDto.getGender());
        pet.setBornDate(createPetDto.getBornDate());
        pet.setPhotoPath(createPetDto.getPhotoPath());
        pet.setChipNumber(createPetDto.getChipNumber());
        this.petDao.createPet(pet);
    }
}
