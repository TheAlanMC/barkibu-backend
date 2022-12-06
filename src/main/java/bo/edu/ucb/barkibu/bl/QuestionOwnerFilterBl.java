package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.CategoryDao;
import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dao.SpecieDao;
import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class QuestionOwnerFilterBl {
    PetQuestionDao petQuestionDao;
    CategoryDao categoryDao;
    SpecieDao specieDao;

    public QuestionOwnerFilterBl(PetQuestionDao petQuestionDao, CategoryDao categoryDao, SpecieDao specieDao) {
        this.petQuestionDao = petQuestionDao;
        this.categoryDao = categoryDao;
        this.specieDao = specieDao;
    }

    public List<PetQuestion> findPetQuestionByKeyWord(QuestionVeterinarianFilterDto questionVeterinarianFilterDto, Pageable pageable) {
        if (!questionVeterinarianFilterDto.getCategoryId().equals("")) {
            if (categoryDao.findCategoryByCategoryId(Integer.parseInt(questionVeterinarianFilterDto.getCategoryId())) == null) {
                throw new BarkibuException("SCTY-4006");
            }
        }
        if (!questionVeterinarianFilterDto.getSpecieId().equals("")) {
            if (specieDao.findSpecieBySpecieId(Integer.parseInt(questionVeterinarianFilterDto.getSpecieId())) == null) {
                throw new BarkibuException("SCTY-4007");
            }
        }
        List<PetQuestion> petQuestions = petQuestionDao.findPetQuestionByKeyWord(questionVeterinarianFilterDto, pageable);
        if (petQuestions.isEmpty()) {
            throw new BarkibuException("SCTY-4011");
        }
        return petQuestions;
    }
}
