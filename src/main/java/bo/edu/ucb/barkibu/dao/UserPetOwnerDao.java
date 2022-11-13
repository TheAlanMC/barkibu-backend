package bo.edu.ucb.barkibu.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface UserPetOwnerDao {
    // Asigna el grupo dueño de mascota a un usuario
    @Insert("""
            INSERT INTO user_group
            (user_id, group_id, status, tx_date, tx_user, tx_host)
            VALUES
            (#{userId}, 2, 'activo', now(), 'anonymus', 'localhost')
            """)
    void addPetOwnerGroup(int userId);
}
