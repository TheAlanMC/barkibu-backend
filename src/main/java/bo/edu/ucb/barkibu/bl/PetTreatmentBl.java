package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetTreatmentDao;
import bo.edu.ucb.barkibu.dto.PetTreatmentDto;
import bo.edu.ucb.barkibu.entity.PetTreatment;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isDateAfterToday;

@Service
public class PetTreatmentBl {
    PetTreatmentDao petTreatmentDao;

    public PetTreatmentBl(PetTreatmentDao petTreatmentDao) {
        this.petTreatmentDao = petTreatmentDao;
    }

    public void createPetTreatment(PetTreatmentDto petTreatmentDto) {
        // Verificamos que la fecha de la última vacuna no sea mayor a la fecha actual
        if (isDateAfterToday(petTreatmentDto.getTreatmentLastDate())) {
            throw new BarkibuException("SCTY-1008", "Date must be before today", HttpStatus.BAD_REQUEST);
        }
        PetTreatment petTreatment = new PetTreatment();
        petTreatment.setPetId(petTreatmentDto.getPetId());
        petTreatment.setTreatmentId(petTreatmentDto.getTreatmentId());
        petTreatment.setTreatmentLastDate(petTreatmentDto.getTreatmentLastDate());
        //TODO: ADD BL FOR NEXT DATE
        petTreatment.setTreatmentNextDate(new Date(petTreatment.getTreatmentLastDate().getTime() + 15768000000L));
        this.petTreatmentDao.createPetTreatment(petTreatment);
    }
}
