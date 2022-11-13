package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.HelpedPet;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HelpedPetDao {

    @Select("""
            SELECT count(answer_id) as total_answers, specie FROM answer
            JOIN question ON answer.question_id = question.question_id
            JOIN pet ON question.pet_id = pet.pet_id
            JOIN breed ON pet.breed_id = breed.breed_id
            JOIN specie ON breed.specie_id = specie.specie_id
            JOIN "user" ON answer.user_id = "user".user_id
            WHERE user_name = #{userName}
            AND answer.status = 'activo'
            AND question.status = 'activo'
            AND pet.status = 'activo'
            AND breed.status = 'activo'
            AND specie.status = 'activo'
            AND "user".status = 'activo'
            GROUP BY specie.specie
            """)
    List<HelpedPet> findHelpedPetByUserName(String userName);
}
