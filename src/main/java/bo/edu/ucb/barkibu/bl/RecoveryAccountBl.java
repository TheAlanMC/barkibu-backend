package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.RecoveryAccountDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.RecoveryAccountReqDto;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RecoveryAccountBl {

    private RecoveryAccountDao recoveryAccountDao;
    private UserDao userDao;

    public RecoveryAccountBl(RecoveryAccountDao recoveryAccountDao, UserDao userDao) {
        this.recoveryAccountDao = recoveryAccountDao;
        this.userDao = userDao;
    }

    public void createRecoveryAccount(RecoveryAccountReqDto recoveryAccountReqDto) {
        if(userDao.findUserIdByEmail(recoveryAccountReqDto.getEmail()) == null) {
            //TODO: SHOW THAT EMAIL DOES NOT EXIST
            throw new BarkibuException("User not found", "SCTY-3000", HttpStatus.NOT_FOUND);
        }
        int userId = userDao.findUserIdByEmail(recoveryAccountReqDto.getEmail());
        System.out.println(userId);
    }
}
