package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.QuestionVeterinarianFilterDto;
import bo.edu.ucb.barkibu.entity.PetQuestion;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PetQuestionDao {
    @Select("""
            SELECT question_id, pet.pet_id, name as pet_name, photo_path, problem, detailed_description as description, time_stamp as posted_date
            FROM question
            JOIN pet ON question.pet_id = pet.pet_id
            JOIN breed ON pet.breed_id = breed.breed_id
            WHERE CAST(question.category_id AS TEXT) like '%' || #{questionVeterinarianFilterDto.categoryId} || '%'
            AND CAST(breed.specie_id AS TEXT) like '%' || #{questionVeterinarianFilterDto.specieId} || '%'
            AND CAST(question.answered AS TEXT) like '%' || #{questionVeterinarianFilterDto.answered} || '%'
            AND question.status = 'activo'
            AND pet.status = 'activo'
            ORDER BY time_stamp DESC
            OFFSET (#{pageable.pageNumber} * #{pageable.pageSize})
            FETCH NEXT #{pageable.pageSize} ROWS ONLY
            """)
    List<PetQuestion> findPetQuestionsByVeterinarianFilter(QuestionVeterinarianFilterDto questionVeterinarianFilterDto, Pageable pageable);

    @Select("""
            SELECT question_id, name as pet_name, photo_path, problem, detailed_description as description, time_stamp as posted_date
            FROM question
            JOIN pet ON question.pet_id = pet.pet_id
            WHERE question.question_id = #{questionId}
            AND question.status = 'activo'
            AND pet.status = 'activo'
            """)
    PetQuestion findPetQuestionByQuestionId(Integer questionId);

    @Select("""
            SELECT pet_id
            FROM question
            WHERE question_id = #{questionId}
            AND status = 'activo'
            """)
    Integer findPetIdByQuestionId(Integer questionId);

    @Select("""
            SELECT symptom.symptom
            FROM symptom_question
            JOIN symptom ON symptom_question.symptom_id = symptom.symptom_id
            WHERE symptom_question.question_id = #{questionId}
            AND symptom.status = 'activo'
            AND symptom_question.status = 'activo'
            """)
    List<String> findSymptomsByQuestionId(Integer questionId);


    @Select("""
              SELECT question_id, pet.pet_id, name as pet_name, photo_path, problem, detailed_description as description, time_stamp as posted_date
                        FROM question
                        JOIN pet ON question.pet_id = pet.pet_id
                        JOIN breed ON pet.breed_id = breed.breed_id
                        WHERE CAST(question.category_id AS TEXT) like '%' || #{questionVeterinarianFilterDto.categoryId} || '%'
                        AND CAST(breed.specie_id AS TEXT) like '%' || #{questionVeterinarianFilterDto.specieId} || '%'
                        AND question.problem like  '%' || #{questionVeterinarianFilterDto.answered} || '%'
                        AND question.answered = true
                        AND question.status = 'activo'
                        AND pet.status = 'activo'
                        ORDER BY time_stamp DESC
                        OFFSET (#{pageable.pageNumber} * #{pageable.pageSize})
                        FETCH NEXT #{pageable.pageSize} ROWS ONLY
            """)
    List<PetQuestion> findPetQuestionByKeyWord(QuestionVeterinarianFilterDto questionVeterinarianFilterDto, Pageable pageable);
}
