package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.AuthReqDto;
import bo.edu.ucb.barkibu.dto.AuthResDto;
import bo.edu.ucb.barkibu.dto.UserDto;
import bo.edu.ucb.barkibu.entity.User;
import org.springframework.stereotype.Service;

@Service
public class SecurityBl {
    private UserDao userDao;

    public SecurityBl(UserDao userDao) {
        this.userDao = userDao;
    }
    public UserDto getUserByPk (Integer userId) {
        User user = userDao.findByPrimaryKey(userId);
        UserDto userDto = new UserDto(user.getUserId(), user.getCityId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserName(), user.getPhotoPath(), user.getDescription());
        return userDto;
    }

    public AuthResDto authenticate(AuthReqDto credentials) {
        AuthResDto result = new AuthResDto();
        String currentPasswordInBCrypt = userDao.findByUserName(credentials.getUserName());
        if (currentPasswordInBCrypt != null) {
            BCrypt.Result verifyResult = BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), currentPasswordInBCrypt);
            if (verifyResult.verified) {
                result.setToken("123456789");
                result.setRefreshToken("987654321");
            } else {
                //TODO: FIX THROWING EXCEPTION
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            //TODO: FIX THROWING EXCEPTION
            throw new RuntimeException("User not found");
        }
        return result;
    }
}
