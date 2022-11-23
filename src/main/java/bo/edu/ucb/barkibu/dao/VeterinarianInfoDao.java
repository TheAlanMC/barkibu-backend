package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.VeterinarianInfo;
import bo.edu.ucb.barkibu.entity.HelpedPet;
import bo.edu.ucb.barkibu.entity.Reputation;
import bo.edu.ucb.barkibu.entity.VeterinarianOwnAnswer;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VeterinarianInfoDao {
    @Select("""
            SELECT first_name, last_name, city, state, country, description, photo_path
            FROM "user"
            LEFT JOIN city ON "user".city_id = city.city_id
            AND city.status = 'activo'
            LEFT JOIN state ON city.state_id = state.state_id
            AND state.status = 'activo'
            LEFT JOIN country ON state.country_id = country.country_id
            AND country.status = 'activo'            
            WHERE user_name = #{userName}
            AND "user".status = 'activo'
            """)
    VeterinarianInfo findVeterinarianInfoByUserName(String userName);

    @Select("""
            SELECT rank
                FROM ( SELECT row_number() OVER (ORDER BY COUNT(user_answer_like_id) DESC NULLS LAST) as rank,
                       count(user_answer_like_id) as total_likes,  user_name FROM (
                       SELECT "user".user_id as veterinarian_id, user_name from "user"
                        JOIN user_group ON "user".user_id = user_group.user_id
                        JOIN "group" ON user_group.group_id = "group".group_id
                        WHERE "group".group_name = 'VETERINARIO'
                        AND "user".status = 'activo'
                        AND "group".status = 'activo'
                        AND user_group.status = 'activo') AS veterianrians
                        LEFT JOIN answer ON veterinarian_id = answer.user_id
                        AND answer.status = 'activo'
                        LEFT JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
                        AND user_answer_like.status = 'activo'
                        AND liked = true
                        GROUP BY user_name
                        ORDER BY total_likes DESC NULLS LAST) AS ranking
            WHERE user_name = #{user_name}
            """)
    Integer findGeneralRankingByUserName(String userName);

    // Ranking Mensual de usuario veterinario
    @Select("""
            SELECT rank
                FROM ( SELECT row_number() OVER (ORDER BY COUNT(user_answer_like_id) DESC NULLS LAST) as rank,
                       count(user_answer_like_id) as total_likes,  user_name FROM (
                       SELECT "user".user_id as veterinarian_id, user_name from "user"
                        JOIN user_group ON "user".user_id = user_group.user_id
                        JOIN "group" ON user_group.group_id = "group".group_id
                        WHERE "group".group_name = 'VETERINARIO'
                        AND "user".status = 'activo'
                        AND "group".status = 'activo'
                        AND user_group.status = 'activo') AS veterianrians
                        LEFT JOIN answer ON veterinarian_id = answer.user_id
                        AND EXTRACT(MONTH from answer.time_stamp) = EXTRACT(MONTH from now())
                        AND EXTRACT(YEAR from answer.time_stamp) = EXTRACT(YEAR from now())
                        AND answer.status = 'activo'
                        LEFT JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
                        AND user_answer_like.status = 'activo'
                        AND liked = true
                        GROUP BY user_name
                        ORDER BY total_likes DESC NULLS LAST) AS ranking
            WHERE user_name = #{user_name}
            """)
    Integer findMonthlyRankingByUserName(String userName);

    @Select("""
            SELECT * FROM(
                (SELECT count(answer_id) as total_answers FROM answer
                JOIN "user" ON answer.user_id = "user".user_id
                WHERE user_name = #{userName}
                AND answer.status = 'activo'
                AND "user".status = 'activo') AS total_answers
            CROSS JOIN
            (SELECT count(user_answer_like_id) AS total_pet_owner_like FROM answer
                JOIN "user" u1 ON answer.user_id = u1.user_id
                JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
                JOIN "user" u2 ON user_answer_like.user_id = u2.user_id
                JOIN user_group ON u2.user_id = user_group.user_id
                WHERE user_group.group_id = 2
                AND u2.user_id NOT IN (SELECT user_id FROM user_group WHERE group_id != 2)
                AND liked = true
                AND u1.user_name = #{userName}
                AND answer.status = 'activo'
                AND u1.status = 'activo'
                AND u2.status = 'activo'
                AND user_answer_like.status = 'activo'
                AND user_group.status = 'activo') AS total_pet_owner_like
            CROSS JOIN
            (SELECT count(user_answer_like_id) AS total_veterinarian_like FROM answer
                JOIN "user" u1 ON answer.user_id = u1.user_id
                JOIN user_answer_like ON answer.answer_id = user_answer_like.answer_id
                JOIN "user" u2 ON user_answer_like.user_id = u2.user_id
                JOIN user_group ON u2.user_id = user_group.user_id
                WHERE user_group.group_id = 3
                AND liked = true
                AND u1.user_name = #{userName}
                AND answer.status = 'activo'
                AND u1.status = 'activo'
                AND u2.status = 'activo'
                AND user_answer_like.status = 'activo'
                AND user_group.status = 'activo') AS total_veterinarian_like)    
            """)
    Reputation findReputationByUserName(String userName);

    @Select("""
            SELECT count (t1.specie_id) as total_answers, specie
            FROM  (SELECT specie.specie_id, user_name 
                    FROM "user"
                    JOIN answer ON "user".user_id = answer.user_id
                    JOIN question ON answer.question_id = question.question_id
                    JOIN pet ON question.pet_id = pet.pet_id
                    JOIN breed ON pet.breed_id = breed.breed_id
                    JOIN specie ON breed.specie_id = specie.specie_id
                    WHERE user_name = #{userName}
                    AND answer.status = 'activo'
                    AND "user".status = 'activo'
                    AND question.status = 'activo'
                    AND pet.status = 'activo'
                    AND breed.status = 'activo'
                    AND specie.status = 'activo') AS t1
            RIGHT JOIN specie ON t1.specie_id = specie.specie_id
            AND specie.status = 'activo'
            GROUP BY specie
            """)
    List<HelpedPet> findHelpedPetByUserName(String userName);

    @Select("""
            SELECT pet_name, photo_path, question, answer, count(user_answer_like_id) AS total_likes, answer_date
            FROM  (SELECT answer_id, pet.name as pet_name, pet.photo_path, problem as question, answer,question.time_stamp as answer_date
                    FROM answer
                    JOIN question ON answer.question_id = question.question_id
                    JOIN pet ON question.pet_id = pet.pet_id
                    JOIN "user" ON answer.user_id = "user".user_id
                    WHERE "user".user_name = #{userName}
                    AND answer.status = 'activo'
                    AND question.status = 'activo'
                    AND pet.status = 'activo'
                    AND "user".status = 'activo'
                    ) AS t1
            LEFT JOIN user_answer_like ON t1.answer_id = user_answer_like.answer_id
            AND liked = true
            AND user_answer_like.status = 'activo'
            GROUP BY pet_name, photo_path, question, answer, answer_date
            ORDER BY answer_date DESC
            """)
    List<VeterinarianOwnAnswer> findVeterinarianAnswersByUserName(String userName);

}
