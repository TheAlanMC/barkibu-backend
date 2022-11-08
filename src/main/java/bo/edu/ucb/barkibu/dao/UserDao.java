package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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
            SELECT password
            FROM "user" 
            WHERE user_name = #{userName}
            AND status = 'activo'
    """)
    String findByUserName(String userName);

    @Insert("""
            INSERT INTO "user" 
            (first_name, last_name, email, user_name, password, status, tx_date, tx_user, tx_host)
            VALUES 
            (#{firstName}, #{lastName}, #{email}, #{userName}, #{password}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createUser(User user);

}

