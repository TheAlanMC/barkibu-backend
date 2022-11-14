package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.QuestionOwnerDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.QuestionOwnerDto;

import bo.edu.ucb.barkibu.dto.VeterinaryDto;
import bo.edu.ucb.barkibu.entity.QuestionOwner;
import bo.edu.ucb.barkibu.entity.User;
import bo.edu.ucb.barkibu.entity.Veterinary;
import bo.edu.ucb.barkibu.util.BarkibuException;

public class QuestionOwnerBl {
    private QuestionOwnerDao questionOwnerDao;
    private final UserDao userDao;

    public QuestionOwnerBl(QuestionOwnerDao questionOwnerDao, UserDao userDao) {
        this.questionOwnerDao = questionOwnerDao;
        this.userDao = userDao;
    }
    public QuestionOwnerDto findOwnerByUserName(String userName){
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        QuestionOwner questionOwner = questionOwnerDao.findOwnerQuestionByUserName(userName);
        if (questionOwner == null) {
            throw new BarkibuException("SCTY-4004");
        }
        QuestionOwnerDto questionOwnerDto = new QuestionOwnerDto();
        questionOwnerDto.setProblem(questionOwner.getProblem());
        questionOwnerDto.setDetailedDescription(questionOwner.getDetailedDescription());
        questionOwnerDto.setAnswered(questionOwner.isAnswered());
        questionOwnerDto.setQuestionDate(questionOwner.getQuestionDate());

        return questionOwnerDto;
    }

}
