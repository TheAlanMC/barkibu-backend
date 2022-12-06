package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface QuestionDao {
    @Insert("""
            INSERT INTO question
            (user_id, category_id, pet_id, problem, detailed_description, answered, time_stamp, status, tx_date, tx_user, tx_host)
            VALUES (#{userId}, #{categoryId}, #{petId}, #{problem}, #{detailedDescription}, false, now(), 'activo', now(), 'anonymus', 'localhost')
            """)
    void createQuestion(Question question);

    @Select("""
            SELECT question_id
            FROM question
            JOIN "user" ON "user".user_id = question.user_id
            WHERE "user".user_name = 'apanique'
            AND question.status = 'activo'
            AND "user".status = 'activo'
            ORDER BY question_id DESC
            LIMIT 1
            """)
    Integer findQuestionId(String userName);

    @Insert("""
            INSERT INTO symptom_question
            (symptom_id, question_id, status, tx_date, tx_user, tx_host)
            VALUES (#{symptomId}, #{questionId}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createSymptomQuestion(Integer symptomId, Integer questionId);
}
