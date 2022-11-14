package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionVeterinarianFilterBl {
    PetQuestionDao petQuestionDao;

    public QuestionVeterinarianFilterBl(PetQuestionDao petQuestionDao) {
        this.petQuestionDao = petQuestionDao;
    }

    public List<PetQuestion> findPetQuestionsByVeterinarianFilter(QuestionVeterinarianFilterDto questionVeterinarianFilterDto) {
        //TODO: MAYBE ADD A VALIDATION HERE
        return petQuestionDao.findPetQuestionsByVeterinarianFilter(questionVeterinarianFilterDto);
    }
}
