package bo.edu.ucb.barkibu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface VeterinarianRankingDao {

    // Ranking General de usuario veterinario
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
                        GROUP BY user_name
                        ORDER BY total_likes DESC NULLS LAST) AS ranking
            WHERE user_name = #{user_name}
            """)
    Integer findMonthlyRankingByUserName(String userName);
}
