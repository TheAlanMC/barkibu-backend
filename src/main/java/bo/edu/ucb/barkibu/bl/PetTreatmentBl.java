package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetDao;
import bo.edu.ucb.barkibu.dao.PetTreatmentDao;
import bo.edu.ucb.barkibu.dto.PetTreatmentDto;
import bo.edu.ucb.barkibu.entity.PetTreatment;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isTimeAfterNow;
import static bo.edu.ucb.barkibu.util.ValidationUtil.isTimeBeforeNow;

@Service
public class PetTreatmentBl {
    PetDao petDao;
    PetTreatmentDao petTreatmentDao;

    public PetTreatmentBl(PetDao petDao, PetTreatmentDao petTreatmentDao) {
        this.petDao = petDao;
        this.petTreatmentDao = petTreatmentDao;
    }

    public void createPetTreatment(PetTreatmentDto petTreatmentDto) {
        // Verificamos que la fecha de la última vacuna no sea mayor a la fecha actual
        if (!isTimeBeforeNow(petTreatmentDto.getTreatmentLastDate())) {
            throw new BarkibuException("SCTY-1008");
        }
        // Verificamos que la fecha de la próxima vacuna sea mayor a la fecha actual
        if (!isTimeAfterNow(petTreatmentDto.getTreatmentNextDate())) {
            throw new BarkibuException("SCTY-1015");
        }

        PetTreatment petTreatment = new PetTreatment();
        petTreatment.setPetId(petTreatmentDto.getPetId());
        petTreatment.setTreatmentId(petTreatmentDto.getTreatmentId());
        petTreatment.setTreatmentLastDate(petTreatmentDto.getTreatmentLastDate());
        petTreatment.setTreatmentNextDate(petTreatmentDto.getTreatmentNextDate());
        this.petTreatmentDao.createPetTreatment(petTreatment);
    }

    public List<PetTreatmentDto> findPetTreatmentByPetId(Integer petId) {
        // Verificamos que la mascota exista
        if (petDao.findPetByPetId(petId) == null) {
            throw new BarkibuException("SCTY-4008");
        }
        List<PetTreatment> petTreatmentListLAstDate = petTreatmentDao.findTreatmentLastDateByPetId(petId);
        List<PetTreatment> petTreatmentListNextDate = petTreatmentDao.findTreatmentNextDateByPetId(petId);
        petTreatmentListNextDate.addAll(petTreatmentListLAstDate);
        List<PetTreatmentDto> petTreatmentDtos = new ArrayList<>();
        for (PetTreatment petTreatment : petTreatmentListNextDate) {
            PetTreatmentDto petTreatmentDto = new PetTreatmentDto();
            petTreatmentDto.setPetTreatmentId(petTreatment.getPetTreatmentId());
            petTreatmentDto.setTreatmentId(petTreatment.getTreatmentId());
            petTreatmentDto.setPetId(petTreatment.getPetId());
            petTreatmentDto.setTreatmentLastDate(petTreatment.getTreatmentLastDate());
            petTreatmentDto.setTreatmentNextDate(petTreatment.getTreatmentNextDate());
            petTreatmentDtos.add(petTreatmentDto);
        }
        return petTreatmentDtos;
    }



    public void updatePetTreatmentDate(PetTreatmentDto petTreatmentDto) {
        if(petDao.findPetByPetId(petTreatmentDto.getPetId()) == null) {
            throw new BarkibuException("SCTY-4008");
        }
        PetTreatment petTreatment = new PetTreatment();
        petTreatment.setPetId(petTreatmentDto.getPetId());
        petTreatment.setTreatmentId(petTreatmentDto.getTreatmentId());
        petTreatment.setTreatmentLastDate(petTreatmentDto.getTreatmentLastDate());
        this.petTreatmentDao.updatePetTreatmentDate(petTreatment);
    }

    public void deletePetTreatment(Integer petTreatmentId) {
        if(petTreatmentDao.findPetTreatmentIdByPetTreatmentId(petTreatmentId) == null) {
            throw new BarkibuException("SCTY-4013");
        }
        this.petTreatmentDao.deletePetTreatment(petTreatmentId);
    }
}
