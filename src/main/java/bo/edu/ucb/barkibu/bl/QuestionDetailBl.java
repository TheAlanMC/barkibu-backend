package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionDetailBl {
    PetQuestionDao petQuestionDao;

    public QuestionDetailBl(PetQuestionDao petQuestionDao) {
        this.petQuestionDao = petQuestionDao;
    }

    public PetQuestion findPetQuestionByQuestionId(Integer questionId) {
        return petQuestionDao.findPetQuestionByQuestionId(questionId);
    }
}
