package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.entity.User;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserBl {
    private UserDao userDao;

    public UserBl(UserDao userDao) {
        this.userDao = userDao;
    }
    public void createUser(CreateUserDto createUserDto) {
        if (userDao.findUserIdByUserName(createUserDto.getUserName()) != null) {
            throw new BarkibuException("SCTY-1002", "User name already exists", HttpStatus.BAD_REQUEST);
        }
        if (userDao.findUserIdByEmail(createUserDto.getEmail()) != null) {
            throw new BarkibuException("SCTY-1003", "Email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setUserName(createUserDto.getUserName());
        String password = BCrypt.withDefaults().hashToString(12, createUserDto.getPassword().toCharArray());
        user.setPassword(password);
        this.userDao.createUser(user);
    }

    public void createPetOwnerUser(CreateUserDto createUserDto) {
        createUser(createUserDto);
        // Obtenemos el id del usuario creado
        int userId = userDao.findUserIdByUserName(createUserDto.getUserName());
        // Asignamos el grupo de dueño de mascota al usuario recien creado
        this.userDao.addPetOwnerGroup(userId);
    }

    public void createVeterinarianUser(CreateUserDto createUserDto) {
        createUser(createUserDto);
        // Obtenemos el id del usuario creado
        int userId = userDao.findUserIdByUserName(createUserDto.getUserName());
        // Asignamos el grupo de veterinario al usuario recien creado
        this.userDao.addVeterinarianGroup(userId);
    }

}
