package bo.edu.ucb.barkibu.bl;


import bo.edu.ucb.barkibu.dao.QuestionDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.CreateQuestionDto;
import bo.edu.ucb.barkibu.entity.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionBl {
    private QuestionDao questionDao;
    private final UserDao userDao;

    public QuestionBl(QuestionDao questionDao, UserDao userDao) {
        this.questionDao = questionDao;
        this.userDao = userDao;
    }

    public void createQuestion(String userName, CreateQuestionDto createQuestionDto) {
        // Crear la consulta
        Question question = new Question();
        //question.setUserId(userDao.findUserIdByUserName(userName));
        question.setUserId(userDao.findUserIdByUserName(userName));
        question.setCategoryId(createQuestionDto.getCategoryId());
        question.setPetId(createQuestionDto.getPetId());
        question.setProblem(createQuestionDto.getProblem());
        question.setDetailedDescription(createQuestionDto.getDetailedDescription());
        question.setAnswered(createQuestionDto.isAnswered());
        question.setQuestionDate(createQuestionDto.getQuestionDate());
        this.questionDao.createQuestion(question);
    }
}
