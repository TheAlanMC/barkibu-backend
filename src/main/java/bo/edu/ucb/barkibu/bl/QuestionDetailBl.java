package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetQuestionInfoDao;
import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dao.VeterinarianAnswerDao;
import bo.edu.ucb.barkibu.dto.PetDataDto;
import bo.edu.ucb.barkibu.entity.PetQuestionInfo;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionDetailBl {
    PetQuestionDao petQuestionDao;
    PetQuestionInfoDao petQuestionInfoDao;
    VeterinarianAnswerDao veterinarianAnswerDao;

    public QuestionDetailBl(PetQuestionDao petQuestionDao, PetQuestionInfoDao petQuestionInfoDao, VeterinarianAnswerDao veterinarianAnswerDao) {
        this.petQuestionDao = petQuestionDao;
        this.petQuestionInfoDao = petQuestionInfoDao;
        this.veterinarianAnswerDao = veterinarianAnswerDao;
    }

    public PetQuestion findPetQuestionByQuestionId(Integer questionId) {
        PetQuestion petQuestion = petQuestionDao.findPetQuestionByQuestionId(questionId);
        if (petQuestion == null) {
            throw new BarkibuException("SCTY-4005");
        }
        return petQuestion;
    }

    public PetDataDto findPetInfoByQuestionId(Integer questionId) {
        // Verificamos que la pregunta exista
        if(petQuestionDao.findPetQuestionByQuestionId(questionId) == null) {
            throw new BarkibuException("SCTY-4005");
        }
        // Verificar si la mascota existe
        if (petQuestionDao.findPetIdByQuestionId(questionId) == null) {
            throw new BarkibuException("SCTY-4008");
        }
        // Obtener la información de la mascota
        PetQuestionInfo petQuestionInfo = petQuestionInfoDao.findPetQuestionInfoByPetId(petQuestionDao.findPetIdByQuestionId(questionId));
        // Obtener la lista de síntomas de la mascota
        List<String> symptoms = petQuestionDao.findSymptomsByQuestionId(questionId);
        // Cargar la información de la mascota en el DTO
        PetDataDto petDataDto = new PetDataDto();
        petDataDto.setSpecie(petQuestionInfo.getSpecie());
        petDataDto.setBreed(petQuestionInfo.getBreed());
        petDataDto.setGender(petQuestionInfo.getGender());
        petDataDto.setBornDate(petQuestionInfo.getBornDate());
        petDataDto.setCastrated(petQuestionInfo.getCastrated());
        petDataDto.setSymptoms(symptoms);
        return petDataDto;
    }

    public List<VeterinarianAnswer> findVeterinarianAnswersByQuestionId(Integer questionId,String username) {
        // Verificamos que la pregunta exista
        if (petQuestionDao.findPetQuestionByQuestionId(questionId) == null) {
            throw new BarkibuException("SCTY-4005");
        }
        // Obtener la lista de respuestas del veterinario
        return veterinarianAnswerDao.findVeterinarianAnswersByQuestionIdAndUserName(questionId, username);
    }
}


