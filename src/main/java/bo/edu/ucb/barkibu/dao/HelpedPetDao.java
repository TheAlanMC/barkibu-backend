package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.HelpedPet;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HelpedPetDao {

    @Select("""
            SELECT count (t1.specie_id) as total_answers, specie
            FROM  (SELECT specie.specie_id, user_name FROM "user"
                    JOIN answer ON "user".user_id = answer.user_id
                    JOIN question ON answer.question_id = question.question_id
                    JOIN pet ON question.pet_id = pet.pet_id
                    JOIN breed ON pet.breed_id = breed.breed_id
                    JOIN specie ON breed.specie_id = specie.specie_id
                    WHERE user_name = #{userName}
                    ) as t1
            RIGHT JOIN specie ON t1.specie_id = specie.specie_id
            GROUP BY specie
            """)
    List<HelpedPet> findHelpedPetByUserName(String userName);
}
