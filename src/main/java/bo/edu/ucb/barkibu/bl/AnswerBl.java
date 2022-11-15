package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.AnswerDao;
import bo.edu.ucb.barkibu.dao.PetQuestionDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.AnswerDto;
import bo.edu.ucb.barkibu.entity.Answer;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

@Service
public class AnswerBl {
    private final UserDao userDao;
    private final AnswerDao answerDao;
    //TODO: CHANTE TO QUESTIONDAO
    private final PetQuestionDao petQuestionDao;

    public AnswerBl(UserDao userDao, AnswerDao answerDao, PetQuestionDao petQuestionDao) {
        this.userDao = userDao;
        this.answerDao = answerDao;
        this.petQuestionDao = petQuestionDao;
    }

    public void createAnswer(String userName, AnswerDto answerDto) {
        if(answerDto.getQuestionId() == null) {
            throw new BarkibuException("SCTY-4005");
        }
        Integer userId = userDao.findUserIdByUserName(userName);
        if (answerDao.findAnswerIdByQuestionIdAndUserId(answerDto.getQuestionId(), userId) != null) {
            throw new BarkibuException("SCTY-1012");
        }
        Answer answer = new Answer();
        answer.setQuestionId(answerDto.getQuestionId());
        answer.setUserId(userId);
        answer.setAnswer(answerDto.getAnswer());
        this.answerDao.addAnswer(answer);
    }

    public void updateAnswer(String userName, AnswerDto answerDto) {
        if(answerDto.getQuestionId() == null) {
            throw new BarkibuException("SCTY-4005");
        }
        Integer userId = userDao.findUserIdByUserName(userName);
        if (answerDao.findAnswerIdByQuestionIdAndUserId(answerDto.getQuestionId(), userId) == null) {
            throw new BarkibuException("SCTY-4009");
        }
        Answer answer = new Answer();
        answer.setQuestionId(answerDto.getQuestionId());
        answer.setUserId(userId);
        answer.setAnswer(answerDto.getAnswer());
        this.answerDao.updateAnswer(answer);
    }

    public void likeAnswer(String userName, Integer answerId) {
        if (answerDao.findAnswerIdByAnswerId(answerId) == null) {
            throw new BarkibuException("SCTY-4009");
        }
        Integer userId = userDao.findUserIdByUserName(userName);
        if(answerDao.findUserAnswerLikeIdByAnswerIdAndUserId(answerId, userId) != null) {
            throw new BarkibuException("SCTY-1013");
        }
        this.answerDao.likeAnswer(answerId, userId);
    }

    //  TODO: IMPLEMENT DISLIKES
 /*   public void dislikeAnswer(String userName, Integer answerId) {
        if (answerDao.findAnswerIdByAnswerId(answerId) == null) {
            throw new BarkibuException("SCTY-4009");
        }
        Integer userId = userDao.findUserIdByUserName(userName);
        if(answerDao.findUserAnswerLikeIdByAnswerIdAndUserId(answerId, userId) == null) {
            throw new BarkibuException("SCTY-1014");
        }

        this.answerDao.dislikeAnswer(answerId, userId);
    }*/
}
