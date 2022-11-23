package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.*;
import bo.edu.ucb.barkibu.dto.*;


import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.BarkibuException;
import bo.edu.ucb.barkibu.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBl {
    private final UserDao userDao;

    public UserBl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void updatePassword(String userName, UpdatePasswordDto updatePasswordDto) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        // Verificamos que la contraseña actual sea correcta
        String currentPasswordInBCrypt = userDao.findPasswordByUserName(userName);
        BCrypt.Result verifyResult = BCrypt.verifyer().verify(updatePasswordDto.getCurrentPassword().toCharArray(),
                currentPasswordInBCrypt);
        if (!verifyResult.verified) {
            throw new BarkibuException("SCTY-1010");
        }
        // Verificamos que la nueva contraseña sea diferente a la actual
        if (updatePasswordDto.getCurrentPassword().equals(updatePasswordDto.getNewPassword())) {
            throw new BarkibuException("SCTY-1011");
        }
        // Verificamos que la nueva contraseña y la confirmación sean iguales
        if (!updatePasswordDto.getNewPassword().equals(updatePasswordDto.getConfirmNewPassword())) {
            throw new BarkibuException("SCTY-1007");
        }
        // Encriptamos la nueva contraseña
        String password = BCrypt.withDefaults().hashToString(12, updatePasswordDto.getNewPassword().toCharArray());
        user.setPassword(password);
        this.userDao.updatePassword(user);
    }

    public List<String> getGroups(String userName) {
        return userDao.findGroupsByUserName(userName);
    }
    public void updateUser(String userName, UpdateUserDto updateUserDto) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if (ValidationUtil.userNameHasBlankSpaces(updateUserDto.getUserName())) {
            throw new BarkibuException("SCTY-1014");
        }
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());
        user.setUserName(updateUserDto.getUserName());

        this.userDao.updateUser(user);
    }

    public UserInfoDto findUserInfoByUserName(String userName) {
        User user = userDao.findInfoUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setFirstName(user.getFirstName());
        userInfoDto.setLastName(user.getLastName());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setUserName(user.getUserName());
        return userInfoDto;
    }
}
