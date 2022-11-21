package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.CategoryDao;
import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dao.SpecieDao;
import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionVeterinarianFilterBl {
    PetQuestionDao petQuestionDao;
    CategoryDao categoryDao;
    SpecieDao specieDao;

    public QuestionVeterinarianFilterBl(PetQuestionDao petQuestionDao, CategoryDao categoryDao, SpecieDao specieDao) {
        this.petQuestionDao = petQuestionDao;
        this.categoryDao = categoryDao;
        this.specieDao = specieDao;
    }

    public List<PetQuestion> findPetQuestionsByVeterinarianFilter(QuestionVeterinarianFilterDto questionVeterinarianFilterDto, Pageable pageable) {
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
        List<PetQuestion> petQuestions = petQuestionDao.findPetQuestionsByVeterinarianFilter(questionVeterinarianFilterDto, pageable);
        if (petQuestions.isEmpty()) {
            throw new BarkibuException("SCTY-4005");
        }
        return petQuestions;
    }
}
