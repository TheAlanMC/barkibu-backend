package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetTreatmentDao;
import bo.edu.ucb.barkibu.dto.PetOwnTreatmentListDto;
import bo.edu.ucb.barkibu.dto.PetTreatmentDto;
import bo.edu.ucb.barkibu.entity.PetTreatment;
import bo.edu.ucb.barkibu.entity.PetTreatmentList;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isTimeAfterNow;

@Service
public class PetTreatmentBl {
    PetTreatmentDao petTreatmentDao;

    public PetTreatmentBl(PetTreatmentDao petTreatmentDao) {
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
        //TODO: ADD BL FOR NEXT DATE
        petTreatment.setTreatmentNextDate(new Date(petTreatment.getTreatmentLastDate().getTime() + 15768000000L));
        this.petTreatmentDao.createPetTreatment(petTreatment);
    }
    /*public List<PetTreatmentDto> findByPetId(Integer petId) {
        PetTreatmentDto petTreatmentDto = PetTreatmentDao.findTreatmentByPetId(petId);
        if (petTreatmentDto == null) {
            throw new BarkibuException("SCTY-4005");
        }
        return PetTreatmentDao.findTreatmentByPetId(petId);
    }

     */
    public List<PetTreatmentList> findPetById(Integer petId) {
        // Verificamos que la mascota exista
        if(petTreatmentDao.findTreatmentByPetId(petId) == null) {
            throw new BarkibuException("SCTY-4005");
        }
        // Obtener la lista de tratamientos
        return petTreatmentDao.findTreatmentByPetId(petId);
    }
}
