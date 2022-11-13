package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VeterinarianAnswerDao {

    @Select("""
            SELECT pet.name as pet_name, pet.photo_path, problem as question, answer,
                sum(pet_owner_like)+sum(veterinarian_like) AS total_likes, question.time_stamp as answer_date
            FROM answer
            JOIN question ON answer.question_id = question.question_id
            JOIN pet ON question.pet_id = pet.pet_id
            JOIN "user" ON answer.user_id = "user".user_id
            WHERE "user".user_name = #{userName}
            AND answer.status = 'activo'
            AND question.status = 'activo'
            AND pet.status = 'activo'
            AND "user".status = 'activo'
            GROUP BY pet.name, pet.photo_path, problem, answer, question.time_stamp
            ORDER BY question.time_stamp DESC;
            """)
    List<VeterinarianAnswer> findVeterinarianAnswersByUserName(String userName);

}
