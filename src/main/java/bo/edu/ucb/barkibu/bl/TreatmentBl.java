package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.SymptomDao;
import bo.edu.ucb.barkibu.dao.TreatmentDao;
import bo.edu.ucb.barkibu.dto.SymptomDto;
import bo.edu.ucb.barkibu.dto.TreatmentDto;
import bo.edu.ucb.barkibu.entity.Symptom;
import bo.edu.ucb.barkibu.entity.Treatment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentBl {
    TreatmentDao treatmentDao;

    public TreatmentBl(TreatmentDao treatmentDao) {
        this.treatmentDao = treatmentDao;
    }

    public List<TreatmentDto> findAllTreatment() {
        List<Treatment> treatment = treatmentDao.findAll();
        List<TreatmentDto> treatmentDto = new ArrayList<>();
        for (Treatment treatments : treatment) {
            treatmentDto.add(new TreatmentDto(treatments.getTreatmentId(), treatments.getTreatment(), treatments.getDescription()));
        }
        return treatmentDto;
    }
}
