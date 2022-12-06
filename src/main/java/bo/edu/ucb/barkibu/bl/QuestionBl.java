package bo.edu.ucb.barkibu.bl;


import bo.edu.ucb.barkibu.dao.QuestionDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.entity.Question;
import bo.edu.ucb.barkibu.entity.SymptomQuestion;
import org.springframework.stereotype.Service;

@Service
public class QuestionBl {
    private QuestionDao questionDao;
    private final UserDao userDao;

    public QuestionBl(QuestionDao questionDao, UserDao userDao) {
        this.questionDao = questionDao;
        this.userDao = userDao;
    }

    public void createQuestion(String userName, SymptomQuestion symptomQuestion) {
        // Crear la consulta
        Question question = new Question();
        question.setUserId(userDao.findUserIdByUserName(userName));
        question.setCategoryId(symptomQuestion.getCategoryId());
        question.setPetId(symptomQuestion.getPetId());
        question.setProblem(symptomQuestion.getProblem());
        question.setDetailedDescription(symptomQuestion.getDetailedDescription());
        this.questionDao.createQuestion(question);
        // Obtenemos el id de la consulta
        question.setQuestionId(this.questionDao.findQuestionId(userName));
        // Crear los intomas
        for (Integer symptomId : symptomQuestion.getSymptomIdList()) {
            this.questionDao.createSymptomQuestion(symptomId, question.getQuestionId());
        }
    }
}
