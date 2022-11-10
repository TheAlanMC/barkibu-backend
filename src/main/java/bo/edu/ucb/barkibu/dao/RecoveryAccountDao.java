package bo.edu.ucb.barkibu.dao;

import org.apache.ibatis.annotations.Insert;

public interface RecoveryAccountDao {

    @Insert("""
            INSERT INTO recovery_account
            (user_id, hash_code, status, tx_date, tx_user, tx_host)
            VALUES (#{userId}, #{hash_code}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createRecoveryAccount(Integer userId, String hash_code);

}
