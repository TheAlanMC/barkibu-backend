package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    @Select("""
            SELECT user_id, city_id, first_name, last_name, email, user_name, photo_path, description
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
    """)
    User findUserByUserName(String userName);

    // Encuentra el id de un usuario por su userName
    @Select("""
            SELECT user_id
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
    """)
    Integer findUserIdByUserName(String userName);

    // Encuentra la contraseña de un usuario por su userName
    @Select("""
            SELECT password
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
    """)
    String findPasswordByUserName(String userName);

    // Encuentra el id de un usuario por su email
    @Select("""
            SELECT user_id
            FROM "user"
            WHERE email = #{email}
            AND status = 'activo'
    """)
    Integer findUserIdByEmail(String email);

    // Crea un usuario con los campos obligatorios
    @Insert("""
            INSERT INTO "user"
            (first_name, last_name, email, user_name, password, status, tx_date, tx_user, tx_host)
            VALUES
            (#{firstName}, #{lastName}, #{email}, #{userName}, #{password}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createUser(User user);



    // Cambio de contraseña
    @Update("""
            UPDATE "user"
            SET password = #{password}
            WHERE user_id = #{userId}
            AND status = 'activo'
            """)
    void updatePassword(User user);

}

