package bo.edu.ucb.barkibu.dao;

import org.apache.ibatis.annotations.Insert;

import java.util.Date;

public interface RecoveryAccountDao {

    @Insert("""
            INSERT INTO recovery_account
            (user_id, hash_code, expiration_date, status, tx_date, tx_user, tx_host)
            VALUES (#{userId}, #{hash_code}, #{expiration_date}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createRecoveryAccount(Integer userId, String hash_code, Date expiration_date);

}
