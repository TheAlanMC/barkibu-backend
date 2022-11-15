package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.VeterinarianOwnAnswer;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VeterinarianOwnAnswerDao {

    @Select("""
            SELECT pet.name as pet_name, pet.photo_path, problem as question, answer,
                   count(user_answer_like_id) AS total_likes, question.time_stamp as answer_date
            FROM answer
            JOIN question ON answer.question_id = question.question_id
            JOIN pet ON question.pet_id = pet.pet_id
            JOIN "user" ON answer.user_id = "user".user_id
            LEFT JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
            AND user_answer_like.status = 'activo'
            WHERE "user".user_name = 'czarate'
            AND answer.status = 'activo'
            AND question.status = 'activo'
            AND pet.status = 'activo'
            AND "user".status = 'activo'
            GROUP BY pet.name, pet.photo_path, problem, answer, question.time_stamp
            ORDER BY question.time_stamp DESC;
            """)
    List<VeterinarianOwnAnswer> findVeterinarianAnswersByUserName(String userName);

}
