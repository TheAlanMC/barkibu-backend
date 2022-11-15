package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Reputation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ReputationDao {
    @Select("""
            SELECT * FROM(
                (SELECT count(answer_id) as total_answers FROM answer
                JOIN "user" ON answer.user_id = "user".user_id
                WHERE user_name = #{userName}
                AND answer.status = 'activo'
                AND "user".status = 'activo') AS total_answers
            CROSS JOIN
            (SELECT count(user_answer_like_id) AS total_pet_owner_like FROM answer
                  JOIN "user" ON answer.user_id = "user".user_id
                  JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
                  JOIN user_group ON user_answer_like.user_id = user_group.user_id
                  WHERE group_id = 3
                  AND liked = true
                  AND user_name = #{userName}
                  AND answer.status = 'activo'
                  AND "user".status = 'activo'
                  AND user_answer_like.status = 'activo'
                  AND user_group.status = 'activo') AS total_pet_owner_like
                  ) AS pet_owner_like
            CROSS JOIN
            (SELECT count(user_answer_like_id) AS total_veterinarian_like  FROM answer
                  JOIN "user" ON answer.user_id = "user".user_id
                  JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
                  JOIN user_group ON user_answer_like.user_id = user_group.user_id
                  WHERE group_id = 2
                  AND liked = true
                  AND user_name = #{userName}
                  AND answer.status = 'activo'
                  AND "user".status = 'activo'
                  AND user_answer_like.status = 'activo'
                  AND user_group.status = 'activo') AS total_veterinarian_like     
            """)
    Reputation findReputationByUserName(String userName);
}
