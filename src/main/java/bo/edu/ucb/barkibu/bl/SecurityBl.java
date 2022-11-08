package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.UserDao;
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
        UserDto userDto = new UserDto(user.getUserId(), user.getCityId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhotoPath(), user.getDescription());
        return userDto;
    }
}
