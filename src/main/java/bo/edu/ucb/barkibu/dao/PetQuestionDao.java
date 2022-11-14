package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PetQuestionDao {
    @Select("""
            SELECT question_id, pet.pet_id, name as pet_name, photo_path, problem, time_stamp as posted_date 
            FROM question
            JOIN pet ON question.pet_id = pet.pet_id
            JOIN breed ON pet.breed_id = breed.breed_id
            WHERE CAST(question.category_id AS TEXT) like '%' || #{categoryId}
            AND CAST(breed.specie_id AS TEXT) like '%' || #{specieId}
            AND CAST(question.answered AS TEXT) like '%' || #{answered}
            AND question.status = 'activo'
            AND pet.status = 'activo'
            ORDER BY time_stamp DESC;
            """)
    List<PetQuestion> findPetQuestionsByVeterinarianFilter(QuestionVeterinarianFilterDto questionVeterinarianFilterDto);

    @Select("""
            SELECT question_id, name as pet_name, photo_path, problem, time_stamp as posted_date
            FROM question
            JOIN pet ON question.pet_id = pet.pet_id
            WHERE question.question_id = #{questionId}
            AND question.status = 'activo'
            AND pet.status = 'activo';
            """)
    PetQuestion findPetQuestionByQuestionId(Integer questionId);
}
