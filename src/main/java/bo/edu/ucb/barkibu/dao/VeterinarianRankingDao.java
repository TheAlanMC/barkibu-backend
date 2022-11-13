package bo.edu.ucb.barkibu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface VeterinarianRankingDao {

    // Ranking General de usuario veterinario
    @Select("""
            SELECT rank FROM (
            SELECT row_number()
                OVER (
                    ORDER BY sum(pet_owner_like)+sum(veterinarian_like) DESC NULLS LAST) AS rank,
                    sum(pet_owner_like)+sum(veterinarian_like) AS total_likes, "user".user_name FROM "user"
            LEFT JOIN answer ON "user".user_id = answer.user_id
            AND answer.status = 'activo'
            WHERE "user".status = 'activo'
            GROUP BY "user".user_name
            ORDER BY total_likes DESC NULLS LAST) AS ranking
            WHERE user_name = #{userName}
            """)
    Integer findGeneralRankingByUserName(String userName);

    // Ranking Mensual de usuario veterinario
    @Select("""
            SELECT rank FROM (
            SELECT row_number()
                OVER (
                    ORDER BY sum(pet_owner_like)+sum(veterinarian_like) DESC NULLS LAST) AS rank,
                    sum(pet_owner_like)+sum(veterinarian_like) AS total_likes, "user".user_name FROM "user"
            LEFT JOIN answer ON "user".user_id = answer.user_id
            AND EXTRACT(MONTH from answer.time_stamp) = EXTRACT(MONTH from now())
            AND EXTRACT(YEAR from answer.time_stamp) = EXTRACT(YEAR from now())
            AND answer.status = 'activo'
            WHERE "user".status = 'activo'
            GROUP BY "user".user_name
            ORDER BY total_likes DESC NULLS LAST) AS ranking
            WHERE user_name = #{userName}
            """)
    Integer findMonthlyRankingByUserName(String userName);
}
