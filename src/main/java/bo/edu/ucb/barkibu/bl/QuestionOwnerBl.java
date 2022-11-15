package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.QuestionOwnerDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.QuestionOwnerDto;

import bo.edu.ucb.barkibu.dto.VeterinaryDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import bo.edu.ucb.barkibu.entity.QuestionOwner;
import bo.edu.ucb.barkibu.entity.User;
import bo.edu.ucb.barkibu.entity.Veterinary;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

@Service
public class QuestionOwnerBl {
    private QuestionOwnerDao questionOwnerDao;


    public QuestionOwnerBl(QuestionOwnerDao questionOwnerDao) {
        this.questionOwnerDao = questionOwnerDao;
    }


    public QuestionOwner findOwnerByUserName(String userName) {
        QuestionOwner questionOwner = questionOwnerDao.findOwnerQuestionByUserName(userName);
        if (questionOwner == null) {
            throw new BarkibuException("SCTY-4005");
        }
        return questionOwner;
    }
   /* public QuestionOwner findOwnerByUserName(String userName){
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        QuestionOwner questionOwner = questionOwnerDao.findOwnerQuestionByUserName(userName);
        if (questionOwner == null) {
            throw new BarkibuException("SCTY-4004");
        }
        QuestionOwner questionOwner1 = new QuestionOwner();
        questionOwner.setQuestionId(questionOwner.getQuestionId());
        questionOwner.setPhotoPath(questionOwner.getPhotoPath());
        questionOwner.setProblem(questionOwner.getProblem());
        questionOwner.setDetailedDescription(questionOwner.getDetailedDescription());
        questionOwner.setQuestionDate(questionOwner.getQuestionDate());

        return questionOwner1;
    }*/
}
