package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.RecoveryAccountDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.RecoveryAccountReqDto;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isEmailValid;

@Service
public class RecoveryAccountBl {

    private RecoveryAccountDao recoveryAccountDao;
    private UserDao userDao;

    public RecoveryAccountBl(RecoveryAccountDao recoveryAccountDao, UserDao userDao) {
        this.recoveryAccountDao = recoveryAccountDao;
        this.userDao = userDao;
    }

    public void createRecoveryAccount(RecoveryAccountReqDto recoveryAccountReqDto) {
        // Verificamos que el email tenga un formato valido
        if (!isEmailValid(recoveryAccountReqDto.getEmail())) {
            throw new BarkibuException("SCTY-1004", "Email format is invalid", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que el email exista
        if(userDao.findUserIdByEmail(recoveryAccountReqDto.getEmail()) == null) {
            //TODO: SHOW THAT EMAIL DOES NOT EXIST?
            throw new BarkibuException("User not found", "SCTY-3000", HttpStatus.NOT_FOUND);
        }
        int userId = userDao.findUserIdByEmail(recoveryAccountReqDto.getEmail());
        int randomCode = (int) (Math.random() * 900000) + 100000;
        String hashCode = BCrypt.withDefaults().hashToString(12, String.valueOf(randomCode).toCharArray());
        Date expirationDate = new Date();
        // Incrementamos la fecha de expiracion en 1 dia
        expirationDate.setTime(expirationDate.getTime() + 86400000);
        //TODO: SEND EMAIL WITH CODE
        //TODO: SECURITY DELETE THIS !!!
        System.out.println("Code: " + randomCode);
        recoveryAccountDao.createRecoveryAccount(userId, hashCode,expirationDate);
    }
}
