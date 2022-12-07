package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.SymptomDao;
import bo.edu.ucb.barkibu.dto.SymptomDto;
import bo.edu.ucb.barkibu.entity.Symptom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SymptomBl {
    SymptomDao symptomDao;

    public SymptomBl(SymptomDao symptomDao) {
        this.symptomDao = symptomDao;
    }

    public List<SymptomDto> findAllSymptom() {
        List<Symptom> symptom = symptomDao.findAll();
        List<SymptomDto> symptomDto = new ArrayList<>();
        for (Symptom symptoms : symptom) {
            symptomDto.add(new SymptomDto(symptoms.getSymptomId(), symptoms.getSymptom()));
        }
        return symptomDto;
    }
}
