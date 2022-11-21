package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetInfoDao;
import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dao.VeterinarianAnswerDao;
import bo.edu.ucb.barkibu.dto.PetInfoDto;
import bo.edu.ucb.barkibu.entity.PetInfo;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class QuestionDetailBl {
    PetQuestionDao petQuestionDao;
    PetInfoDao petInfoDao;
    VeterinarianAnswerDao veterinarianAnswerDao;

    public QuestionDetailBl(PetQuestionDao petQuestionDao, PetInfoDao petInfoDao, VeterinarianAnswerDao veterinarianAnswerDao) {
        this.petQuestionDao = petQuestionDao;
        this.petInfoDao = petInfoDao;
        this.veterinarianAnswerDao = veterinarianAnswerDao;
    }

    public PetQuestion findPetQuestionByQuestionId(Integer questionId) {
        PetQuestion petQuestion = petQuestionDao.findPetQuestionByQuestionId(questionId);
        if (petQuestion == null) {
            throw new BarkibuException("SCTY-4005");
        }
        return petQuestion;
    }

    public PetInfoDto findPetInfoByQuestionId(Integer questionId) {
        // Verificamos que la pregunta exista
        if(petQuestionDao.findPetQuestionByQuestionId(questionId) == null) {
            throw new BarkibuException("SCTY-4005");
        }
        // Verificar si la mascota existe
        if (petQuestionDao.findPetIdByQuestionId(questionId) == null) {
            throw new BarkibuException("SCTY-4008");
        }
        // Obtener la información de la mascota
        PetInfo petInfo = petInfoDao.findPetInfoByPetId(petQuestionDao.findPetIdByQuestionId(questionId));
        // Obtener la lista de síntomas de la mascota
        List<String> symptoms = petQuestionDao.findSymptomsByQuestionId(questionId);
        // Obtener la edad de la mascota
        Date bornDate = petInfo.getBornDate();
        Date currentDate = new Date();
        Period period = Period.between(bornDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
                currentDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        // Cargar la información de la mascota en el DTO
        PetInfoDto petInfoDto = new PetInfoDto();
        petInfoDto.setSpecie(petInfo.getSpecie());
        petInfoDto.setBreed(petInfo.getBreed());
        petInfoDto.setGender(petInfo.getGender());
        petInfoDto.setBornDate(bornDate);
        petInfoDto.setCastrated(petInfo.getCastrated());
        petInfoDto.setSymptoms(symptoms);
        return petInfoDto;
    }

    public List<VeterinarianAnswer> findVeterinarianAnswersByQuestionId(Integer questionId) {
        // Verificamos que la pregunta exista
        if(petQuestionDao.findPetQuestionByQuestionId(questionId) == null) {
            throw new BarkibuException("SCTY-4005");
        }
        // Obtener la lista de respuestas del veterinario
        return veterinarianAnswerDao.findVeterinarianAnswersByQuestionId(questionId);
    }
}
