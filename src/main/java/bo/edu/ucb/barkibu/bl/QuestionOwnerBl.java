package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.QuestionOwnerDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.QuestionOwnerDto;

import bo.edu.ucb.barkibu.dto.VeterinaryDto;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionOwnerBl {
    private QuestionOwnerDao questionOwnerDao;


    public QuestionOwnerBl(QuestionOwnerDao questionOwnerDao) {
        this.questionOwnerDao = questionOwnerDao;
    }


    public List<QuestionOwner> findOwnerByUserName(String userName) {
        // Verificamos que la mascota exista
        if(questionOwnerDao.findOwnerQuestionByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4005");
        }
        // Obtener la lista de tratamientos
        return questionOwnerDao.findOwnerQuestionByUserName(userName);
    }
}
