package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Reputation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ReputationDao {
    @Select("""
            SELECT count(answer_id) AS total_answers, sum(pet_owner_like) AS total_pet_owner_like,
                    sum(veterinarian_like) AS total_veterinarian_like FROM answer
            JOIN "user" ON answer.user_id = "user".user_id
            WHERE user_name = #{userName}
            AND answer.status = 'activo'
            AND "user".status = 'activo'
            """)
    Reputation findReputationByUserName(String userName);
}
