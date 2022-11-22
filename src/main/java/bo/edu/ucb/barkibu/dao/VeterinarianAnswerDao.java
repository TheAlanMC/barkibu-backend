package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VeterinarianAnswerDao {
    @Select("""
            SELECT answer.answer_id,
                CASE WHEN c.answer_id IS NULL THEN false ELSE true END AS liked,
                CASE WHEN d.user_id IS NULL THEN false ELSE true END AS answered,
                a.first_name as veterinarian_first_name, a.last_name as veterinarian_last_name,
                answer.answer, count(b.user_answer_like_id) as total_likes, answer.time_stamp as answer_date
            FROM answer
            JOIN "user" a ON answer.user_id = a.user_id
            LEFT JOIN user_answer_like b ON answer.answer_id = b.answer_id
            LEFT JOIN user_answer_like c ON answer.answer_id = c.answer_id
            AND  c.user_id = (SELECT user_id FROM "user" WHERE user_name = #{userName})
            LEFT JOIN answer d ON answer.answer_id = d.answer_id
            AND d.user_id = (SELECT user_id FROM "user" WHERE user_name = #{userName})
            WHERE answer.question_id = #{questionId}
            GROUP BY answer.answer_id, c.answer_id, d.user_id, a.first_name, a.last_name, answer.answer, answer.time_stamp
            ORDER BY total_likes DESC, answer_date DESC;
            """)
    List<VeterinarianAnswer> findVeterinarianAnswersByQuestionIdAndUserName(Integer questionId, String userName);
}
