package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface UserVeterinarianDao {

    // Asigna el grupo veterinario a un usuario
    @Insert("""
            INSERT INTO user_group
            (user_id, group_id, status, tx_date, tx_user, tx_host)
            VALUES
            (#{userId}, 3, 'activo', now(), 'anonymus', 'localhost')
            """)
    void addVeterinarianGroup(int userId);

    // Actualiza la información de un usuario Veterinario
    @Update("""
            UPDATE "user"
            SET city_id = #{cityId}, first_name = #{firstName}, last_name = #{lastName}, email = #{email},
                user_name = #{userName}, photo_path = #{photoPath}, description = #{description}
            WHERE user_id = #{userId}
            AND status = 'activo'
            """)
    void updateUser(User user);
}
