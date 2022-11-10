package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    @Select("""
            SELECT user_id, city_id,first_name,last_name,email,
                   user_name,password,photo_path,description,status,
                   tx_date,tx_user,tx_host 
            FROM "user" 
            WHERE user_id = #{userId}
            AND status = 'activo' 
    """)
    User findByPrimaryKey(Integer userId);

    @Select("""
            SELECT user_id, city_id,first_name,last_name,email,
                   user_name,password,photo_path,description,status,
                   tx_date,tx_user,tx_host 
            FROM "user" 
            WHERE user_name = #{userName}
            AND status = 'activo' 
    """)
    User findByUserName(String userName);

    @Select("""
            SELECT password
            FROM "user" 
            WHERE user_name = #{userName}
            AND status = 'activo'
    """)
    String findPasswordByUserName(String userName);

    @Insert("""
            INSERT INTO "user" 
            (first_name, last_name, email, user_name, password, status, tx_date, tx_user, tx_host)
            VALUES 
            (#{firstName}, #{lastName}, #{email}, #{userName}, #{password}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createUser(User user);

    @Select("""
            SELECT user_name 
            FROM "user" 
            WHERE user_name = #{userName}
            AND status = 'activo' 
    """)
    String findUserName(String userName);

    @Select("""
            SELECT email 
            FROM "user" 
            WHERE email = #{email}
            AND status = 'activo' 
    """)
    String findEmail(String email);

    @Select("""
            SELECT user_id
            FROM "user" 
            WHERE user_name = #{userName}
            AND status = 'activo' 
    """)
    Integer findUserIdByUserName(String userName);
    @Insert("""
            INSERT INTO user_group
            (user_id, group_id, status, tx_date, tx_user, tx_host)
            VALUES 
            (#{userId}, 1, 'activo', now(), 'anonymus', 'localhost')
            """)
    void addPetOwnerGroup(int userId);
}

