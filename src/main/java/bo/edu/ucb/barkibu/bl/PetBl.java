package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetDao;

import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.entity.PetInfoId;
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
        pet.setChipNumber(createPetDto.getChipNumber());
        this.petDao.createPet(pet);
    }


    public PetInfo findPetInfoByPetId(Integer petId) {
        Pet pet = petDao.findPetInfoByPetId(petId);
        if (pet == null) {
            throw new BarkibuException("SCTY-4008");
        }
        PetInfo petInfo = new PetInfo();
        petInfo.setName(pet.getName());
        petInfo.setBorn_date(pet.getBornDate());
        petInfo.setChip_number(pet.getChipNumber());
        return petInfo;
    }

    public PetInfoIdDto findPetInfoById(Integer specieId) {
        PetInfoId petInfoId = petDao.findPetInfoById(specieId);
        if (petInfoId == null) {
           throw new BarkibuException("SCTY-4008");
        }
        PetInfoIdDto petInfoIdDto = new PetInfoIdDto();
        petInfoIdDto.setName(petInfoId.getName());
        petInfoIdDto.setSpecie(petInfoId.getSpecie());
        petInfoIdDto.setBreed(petInfoId.getBreed());
        petInfoIdDto.setChip_number(petInfoId.getChipNumber());
        petInfoIdDto.setBorn_date(petInfoId.getBornDate());
        petInfoIdDto.setPhotoPath(petInfoId.getPhotoPath());


        return petInfoIdDto;
    }
    public List<String> getGroups(String name) {
        return userDao.findGroupsByUserName(name);
    }
    public void updatePet(Integer PetId, UpdatePetDto updatePetDto) {
        Pet pet = petDao.findPetByPetName(PetId);
        if (pet == null) {
            throw new BarkibuException("SCTY-4009");
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
