package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("""
        SELECT user_id, city_id,first_name,last_name,email,password,
               photo_path,description,status,tx_date,tx_user,tx_host 
        FROM "user" 
        WHERE user_id = #{userId}
        AND status = 'activo' 
    """)
    User findByPrimaryKey(Integer userId);

}

