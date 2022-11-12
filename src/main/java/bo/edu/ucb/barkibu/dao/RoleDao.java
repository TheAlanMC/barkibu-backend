package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleDao {
    // Devuelve todos los roles a partir del userName
    @Select("""
            SELECT role.role_id, role_name, role.description, role.status, role.tx_date, role.tx_user, role.tx_host
            FROM role
            JOIN group_role ON role.role_id = group_role.role_id
            JOIN user_group ON group_role.group_id = user_group.group_id
            JOIN "user" ON user_group.user_id = "user".user_id
            WHERE "user".user_name = #{userName}
            AND role.status = 'activo'
            AND group_role.status = 'activo'
            AND user_group.status = 'activo'
            AND "user".status = 'activo'        
            """)
    public List<Role> findRolesByUserName(String userName);


}
