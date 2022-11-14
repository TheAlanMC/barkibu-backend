package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VeterinarianAnswerDao {
    @Select("""
            SELECT answer_id, first_name as veterinarian_name, last_name as veterinarian_last_name, answer, 
                   sum(pet_owner_like)+sum(veterinarian_like) AS total_likes, time_stamp as answer_date 
            FROM answer
            JOIN "user" ON answer.user_id = "user".user_id
            WHERE question_id = #{questionId}
            AND answer.status = 'activo'
            AND "user".status = 'activo'
            GROUP BY answer_id, first_name, last_name, answer, time_stamp
            ORDER BY total_likes DESC;
            """)
    List<VeterinarianAnswer> findVeterinarianAnswersByQuestionId(Integer questionId);
}
