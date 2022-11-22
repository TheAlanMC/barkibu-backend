package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.VeterinarianUserDto;
import bo.edu.ucb.barkibu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface UserDao {

    @Select("""
            SELECT user_id, city_id, first_name, last_name, email, user_name, photo_path, description
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    User findUserByUserName(String userName);

    @Select("""
            SELECT failed_login_time
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    Date findFailedLoginTimeByUserName(String userName);

    @Select("""
            SELECT failed_login_attempts
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    Integer findFailedLoginAttemptsByUserName(String userName);

    @Update("""
            UPDATE "user"
            SET failed_login_time = #{failedLoginTime}
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    void updateFailedLoginTimeByUserName(String userName, Date failedLoginTime);

    @Update("""
            UPDATE "user"
            SET failed_login_attempts = #{failedLoginAttempts}
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    void updateFailedLoginAttemptsByUserName(String userName, Integer failedLoginAttempts);

    @Update("""
            UPDATE "user"
            SET failed_login_attempts = null,
            failed_login_time = null
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    void resetFailedLoginByUserName(String userName);
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

    @Select("""
            SELECT group_name FROM "group"
            JOIN user_group ON "group".group_id = user_group.group_id
            JOIN "user" ON user_group.user_id = "user".user_id
            WHERE "user".user_name = #{userName}
            AND "group".status = 'activo'
            AND user_group.status = 'activo'
            AND "user".status = 'activo'
           """)
    List<String> findGroupsByUserName(String userName);
    //Actualiza datos del usuario
    @Update("""
            UPDATE "user"
            SET first_name = #{firstName}, last_name = #{lastName}, email = #{email},
                user_name = #{userName}
            WHERE user_id = #{userId}
            AND status = 'activo'
            """)
    void updateUser(User user);

    @Select("""
            SELECT first_name, last_name, city.city_id, state.state_id, country.country_id, user_name, email, description, photo_path
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
    VeterinarianUserDto findVeterinarianUserByUserName(String userName);
    //Informacion de usuario
    @Select("""
            SELECT first_name, last_name, email, user_name
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    User findInfoUserByUserName(String userName);

}

