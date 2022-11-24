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
            throw new BarkibuException("SCTY-1008");
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
        PetInfo petInfo = petDao.findPetInfoByPetId(petId);
        if (petInfo == null) {
            throw new BarkibuException("SCTY-4008");
        }
        return petInfo;
    }

    public void updatePet(Integer petId, PetData petData) {
        Pet pet = petDao.findPetByPetId(petId);
        if (pet == null) {
            throw new BarkibuException("SCTY-4008");
        }
        pet.setPetId(petId);
        pet.setName(petData.getName());
        pet.setGender(petData.getGender());
        pet.setCastrated(petData.getCastrated());
        pet.setBreedId(petData.getBreedId());
        pet.setBornDate(petData.getBornDate());
        pet.setPhotoPath(petData.getPhotoPath());
        pet.setChipNumber(petData.getChipNumber());
        this.petDao.updatePet(pet);
    }
    //Eliminación mascota por id
    public void deletePet(Integer petId, Pet deletePet) {
        Pet pet = petDao.findPetByPetId(petId);
        if (pet == null) {
            throw new BarkibuException("SCTY-4008");
        }
        pet.setPetId(petId);
        pet.setStatus(deletePet.getStatus());
        this.petDao.updatePet(pet);
    }

    // Listado de las mascotas
    public List<PetInfo> findPetInfoByUserName(String userName) {
        List<PetInfo> petInfos = petDao.findPetInfoByUserName(userName);
        if (petInfos.isEmpty()) {
            throw new BarkibuException("SCTY-4008");
        }
        return petInfos;
    }

    // Datos mascota perfil
    public PetData findPetByPetId(Integer petId) {
        PetData petData = petDao.findPetDataByPetId(petId);
        if (petData == null) {
            throw new BarkibuException("SCTY-4008");
        }
        return petData;
    }

}
