package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetInfoDao;
import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dto.PetInfoDto;
import bo.edu.ucb.barkibu.entity.PetInfo;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class QuestionDetailBl {
    PetQuestionDao petQuestionDao;
    PetInfoDao petInfoDao;

    public QuestionDetailBl(PetQuestionDao petQuestionDao, PetInfoDao petInfoDao) {
        this.petQuestionDao = petQuestionDao;
        this.petInfoDao = petInfoDao;
    }

    public PetQuestion findPetQuestionByQuestionId(Integer questionId) {
        return petQuestionDao.findPetQuestionByQuestionId(questionId);
    }

    public PetInfoDto findPetInfoByQuestionId(Integer questionId) {
        Integer petId = petQuestionDao.findPetIdByQuestionId(questionId);
        PetInfo petInfo = petInfoDao.findPetInfoByPetId(petId);
        List<String> symptoms = petQuestionDao.findSymptomsByQuestionId(questionId);
        PetInfoDto petInfoDto = new PetInfoDto();
        petInfoDto.setSpecie(petInfo.getSpecie());
        petInfoDto.setBreed(petInfo.getBreed());
        petInfoDto.setGender(petInfo.getGender());
        Date bornDate = petInfo.getBornDate();
        Date currentDate = new Date();
        Period period = Period.between(bornDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
                currentDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());

        if (period.getYears() < 1) {
            petInfoDto.setAge(period.getMonths() + " meses");
        } else {
            petInfoDto.setAge(period.getYears() + " años");
        }
        if (petInfo.getCastrated()) {
            petInfoDto.setCastrated("Sí");
        } else {
            petInfoDto.setCastrated("No");
        }
        petInfoDto.setSymptoms(symptoms);
        return petInfoDto;
    }
}
