package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface QuestionDao {
    @Insert("""
            INSERT INTO question
            (user_id, category_id, pet_id, problem, detailed_description, answered, time_stamp, status, tx_date, tx_user, tx_host)
            VALUES (#{userId}, #{categoryId}, #{petId}, #{problem}, #{detailedDescription}, #{answered}, #{questionDate}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createQuestion(Question question);
}
