package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetDao;
import bo.edu.ucb.barkibu.dto.CreatePetDto;
import bo.edu.ucb.barkibu.entity.Pet;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isDateAfterToday;

@Service
public class PetBl {
    private final PetDao petDao;

    public PetBl(PetDao petDao) {
        this.petDao = petDao;
    }

    public void createPet(CreatePetDto createPetDto) {
        // Verificamos que la fecha de nacimiento no sea mayor a la fecha actual
        if (isDateAfterToday(createPetDto.getBornDate())) {
            throw new  BarkibuException("SCTY-1008");
        }
        // Crear la mascota
        Pet pet = new Pet();
        pet.setUserId(createPetDto.getUserId());
        pet.setBreedId(createPetDto.getBreedId());
        pet.setName(createPetDto.getName());
        pet.setGender(createPetDto.getGender());
        pet.setBornDate(createPetDto.getBornDate());
        pet.setPhotoPath(createPetDto.getPhotoPath());
        // TODO: ADD BL TO CREATE CHIP NUMBER
        String chipNumber = "";
        pet.setChipNumber(chipNumber);
        this.petDao.createPet(pet);
    }
}
