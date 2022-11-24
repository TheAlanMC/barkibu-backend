package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetDao;
import bo.edu.ucb.barkibu.dao.PetTreatmentDao;
import bo.edu.ucb.barkibu.dto.PetTreatmentDto;
import bo.edu.ucb.barkibu.entity.PetTreatment;
import bo.edu.ucb.barkibu.entity.PetTreatmentList;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.List;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isTimeAfterNow;

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
        if (isTimeAfterNow(petTreatmentDto.getTreatmentLastDate())) {
            throw new BarkibuException("SCTY-1008");
        }
        PetTreatment petTreatment = new PetTreatment();
        petTreatment.setPetId(petTreatmentDto.getPetId());
        petTreatment.setTreatmentId(petTreatmentDto.getTreatmentId());
        petTreatment.setTreatmentLastDate(petTreatmentDto.getTreatmentLastDate());
        petTreatment.setTreatmentNextDate(petTreatmentDto.getTreatmentNextDate());
        this.petTreatmentDao.createPetTreatment(petTreatment);
    }

    public List<PetTreatmentList> findPetTreatmentByPetId(Integer petId) {
        // Verificamos que la mascota exista
        if(petDao.findPetByPetId(petId) == null) {
            throw new BarkibuException("SCTY-4008");
        }
        List <PetTreatmentList> petTreatmentList = petTreatmentDao.findTreatmentLastDateByPetId(petId);
        List <PetTreatmentList> petTreatmentList2 = petTreatmentDao.findTreatmentNextDateByPetId(petId);
        petTreatmentList.addAll(petTreatmentList2);
        // Obtener la lista de tratamientos
        return petTreatmentList;
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

}
