package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.RecoveryAccountDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.RecoveryAccountReqDto;
import bo.edu.ucb.barkibu.dto.RecoveryPasswordDto;
import bo.edu.ucb.barkibu.dto.RecoveryPasswordReqDto;
import bo.edu.ucb.barkibu.entity.RecoveryAccount;
import bo.edu.ucb.barkibu.entity.User;
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
        RecoveryAccount recoveryAccount = new RecoveryAccount();
        // Verificamos que el email tenga un formato valido
        if (!isEmailValid(recoveryAccountReqDto.getEmail())) {
            throw new BarkibuException("SCTY-1004", "Email format is invalid", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que el email exista
        if (userDao.findUserIdByEmail(recoveryAccountReqDto.getEmail()) == null) {
            //TODO: SHOW THAT EMAIL DOES NOT EXIST?
            throw new BarkibuException("User not found", "SCTY-3000", HttpStatus.NOT_FOUND);
        }
        int userId = userDao.findUserIdByEmail(recoveryAccountReqDto.getEmail());
        // Cambiamos el estado a inactivo de solicitudes anteriores
        if (recoveryAccountDao.findStatusByUserId(userId) != null) {
            recoveryAccountDao.updateStatusByUserId(userId);
        }
        recoveryAccount.setUserId(userId);
        // Generamos un token aleatorio de 6 digitos
        int randomCode = (int) (Math.random() * 900000) + 100000;
        // Encriptamos el token
        String hashCode = BCrypt.withDefaults().hashToString(12, String.valueOf(randomCode).toCharArray());
        recoveryAccount.setHashCode(hashCode);
        Date expirationDate = new Date();
        // Incrementamos la fecha de expiracion en 1 dia
        expirationDate.setTime(expirationDate.getTime() + 86400000);
        recoveryAccount.setExpirationDate(expirationDate);
        //TODO: SEND EMAIL WITH CODE
        //TODO: SECURITY DELETE THIS !!!
        System.out.println("Code: " + randomCode);
        recoveryAccountDao.createRecoveryAccount(recoveryAccount);
    }

    public void validateCode(RecoveryPasswordReqDto recoveryPasswordReqDto){
        RecoveryAccount recoveryAccount;
        // Verificamos que el email tenga un formato valido
        if (!isEmailValid(recoveryPasswordReqDto.getEmail())) {
            throw new BarkibuException("SCTY-1004", "Email format is invalid", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que el email exista
        if (userDao.findUserIdByEmail(recoveryPasswordReqDto.getEmail()) == null) {
            //TODO: SHOW THAT EMAIL DOES NOT EXIST?
            throw new BarkibuException("User not found", "SCTY-3000", HttpStatus.NOT_FOUND);
        }
        int userId = userDao.findUserIdByEmail(recoveryPasswordReqDto.getEmail());
        recoveryAccount = recoveryAccountDao.findRecoveryAccountByUserId(userId);
        // Verificamos que el codigo sea correcto
        BCrypt.Result verifyResult = BCrypt.verifyer().verify(recoveryPasswordReqDto.getHashCode().toCharArray(), recoveryAccount.getHashCode());
        if (!verifyResult.verified) {
            throw new BarkibuException("SCTY-1005", "Code is invalid", HttpStatus.BAD_REQUEST);
        }
    }
    public void updatePassword(RecoveryPasswordDto recoveryPasswordDto) {
        User user = new User();
        RecoveryAccount recoveryAccount;
        // Verificamos que el email tenga un formato valido
        if (!isEmailValid(recoveryPasswordDto.getEmail())) {
            throw new BarkibuException("SCTY-1004", "Email format is invalid", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que el email exista
        if (userDao.findUserIdByEmail(recoveryPasswordDto.getEmail()) == null) {
            //TODO: SHOW THAT EMAIL DOES NOT EXIST?
            throw new BarkibuException("User not found", "SCTY-3000", HttpStatus.NOT_FOUND);
        }
        int userId = userDao.findUserIdByEmail(recoveryPasswordDto.getEmail());
        recoveryAccount = recoveryAccountDao.findRecoveryAccountByUserId(userId);
        // Verificamos que el token sea valido
        BCrypt.Result verifyResult = BCrypt.verifyer().verify(recoveryPasswordDto.getHashCode().toCharArray(), recoveryAccount.getHashCode());
        if (!verifyResult.verified) {
            throw new BarkibuException("SCTY-1005", "Code is invalid", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que el token no este expirado
        if (recoveryAccount.getExpirationDate().before(new Date())) {
            throw new BarkibuException("SCTY-1006", "Code is expired", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que ambas contraseñas sean iguales
        if (!recoveryPasswordDto.getPassword().equals(recoveryPasswordDto.getConfirmPassword())) {
            throw new BarkibuException("SCTY-1007", "Passwords are not equals", HttpStatus.BAD_REQUEST);
        }
        // Encriptamos la nueva contraseña
        String password = BCrypt.withDefaults().hashToString(12, recoveryPasswordDto.getPassword().toCharArray());
        user.setPassword(password);
        user.setUserId(userId);
        userDao.updatePassword(user);
        recoveryAccountDao.updateStatusByUserId(userId);
    }
}
