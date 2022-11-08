package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserBl {
    private UserDao userDao;

    public UserBl(UserDao userDao) {
        this.userDao = userDao;
    }
    public void createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setUserName(createUserDto.getUserName());
        String password = BCrypt.withDefaults().hashToString(12, createUserDto.getPassword().toCharArray());
        user.setPassword(password);
        this.userDao.createUser(user);
    }
}
