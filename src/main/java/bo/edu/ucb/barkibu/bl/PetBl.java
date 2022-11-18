package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.List;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isTimeAfterNow;

@Service
public class PetBl {
    private final PetDao petDao;
    private final UserDao userDao;

    public PetBl(PetDao petDao, UserDao userDao) {
        this.petDao = petDao;
        this.userDao = userDao;
    }

    public void createPet(String userName, CreatePetDto createPetDto) {
        // Verificamos que la fecha de nacimiento no sea mayor a la fecha actual
        if (isTimeAfterNow(createPetDto.getBornDate())) {
            throw new  BarkibuException("SCTY-1008");
        }
        // Crear la mascota
        Pet pet = new Pet();
        pet.setUserId(userDao.findUserIdByUserName(userName));
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


    public PetDataDto findPetInfoByPetId(Integer specieId) {
        Pet pet = petDao.findPetInfoByPetId(specieId);
        if (pet == null) {
            throw new BarkibuException("SCTY-4000");
        }
        PetDataDto petInfoDto = new PetDataDto();
        petInfoDto.setName(pet.getName());
        petInfoDto.setBorn_date(pet.getBornDate());
        petInfoDto.setChip_number(pet.getChipNumber());
        return petInfoDto;
    }
    public List<String> getGroups(String name) {
        return userDao.findGroupsByUserName(name);
    }
    public void updatePet(Integer PetId, UpdatePetDto updatePetDto) {

        System.out.print(PetId);
        Pet pet = petDao.findPetByPetName(PetId);
        if (pet == null) {
            throw new BarkibuException("SCTY-4000");
        }
        pet.setName(updatePetDto.getName());
        pet.setGender(updatePetDto.getGender());
        pet.setBreedId(updatePetDto.getBreedId());
        pet.setCastrated(updatePetDto.getCastrated());
        pet.setBornDate(updatePetDto.getBornDate());
        pet.setChipNumber(updatePetDto.getChipNumber());

        this.petDao.updatePet(pet);
    }
}
