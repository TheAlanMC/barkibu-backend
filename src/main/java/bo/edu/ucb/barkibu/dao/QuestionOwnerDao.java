package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.QuestionOwner;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionOwnerDao {
    @Select("""
            SELECT pet.photo_path, question_id, problem as question, detailed_description, time_stamp
            FROM question
            JOIN category ON question.category_id = category.category_id
            JOIN pet ON question.pet_id = pet.pet_id
            JOIN "user" ON question.user_id = "user".user_id
            WHERE "user".user_name = #{userName}
            AND question.status = 'activo'
            AND category.status = 'activo'
            AND pet.status = 'activo'
            AND "user".status = 'activo'
            GROUP BY pet.photo_path, question_id, problem, detailed_description, question.time_stamp
            ORDER BY question.time_stamp DESC;
            """)
    QuestionOwner findOwnerQuestionByUserName(String userName);
}
