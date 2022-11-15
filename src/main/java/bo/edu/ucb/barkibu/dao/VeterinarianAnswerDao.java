package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VeterinarianAnswerDao {
    @Select("""
            SELECT answer.answer_id, first_name as veterinarian_name, last_name as veterinarian_last_name, answer, count(user_answer_like_id) as total_likes, time_stamp as answer_date
            FROM answer
            JOIN "user" ON answer.user_id = "user".user_id
            LEFT JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
            AND user_answer_like.status = 'activo'
            WHERE question_id = #{questionId}
            AND liked = true
            AND answer.status = 'activo'
            AND "user".status = 'activo'
            GROUP BY answer.answer_id, first_name, last_name, answer, time_stamp
            ORDER BY total_likes DESC;
            """)
    List<VeterinarianAnswer> findVeterinarianAnswersByQuestionId(Integer questionId);
}
