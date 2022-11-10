package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.RecoveryAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface RecoveryAccountDao {

    @Insert("""
            INSERT INTO recovery_account
            (user_id, hash_code, expiration_date, status, tx_date, tx_user, tx_host)
            VALUES (#{userId}, #{hashCode}, #{expirationDate}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createRecoveryAccount(RecoveryAccount recoveryAccount);

    @Select("""
            SELECT status
            FROM recovery_account
            WHERE user_id = #{userId}
            AND status = 'activo'
            """)
    String findStatusByUserId(Integer userId);

    @Update("""
            UPDATE recovery_account
            SET status = 'inactivo'
            WHERE user_id = #{userId}
            AND status = 'activo'
            """)
    void updateStatusByUserId(Integer userId);

    @Select("""
            SELECT hash_code, expiration_date
            FROM recovery_account
            WHERE user_id = #{userId}
            AND status = 'activo'
            """)
    RecoveryAccount findRecoveryAccountByUserId(Integer userId);
}
