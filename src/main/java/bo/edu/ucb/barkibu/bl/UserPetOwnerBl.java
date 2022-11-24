package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dao.UserPetOwnerDao;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.UserDto;
import bo.edu.ucb.barkibu.entity.User;
import bo.edu.ucb.barkibu.util.BarkibuException;
import bo.edu.ucb.barkibu.util.ValidationUtil;
import org.springframework.stereotype.Service;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isEmailValid;

@Service
public class UserPetOwnerBl {
    final  private UserDao userDao;
    final  private UserPetOwnerDao userPetOwnerDao;

    public UserPetOwnerBl(UserDao userDao, UserPetOwnerDao userPetOwnerDao) {
        this.userDao = userDao;
        this.userPetOwnerDao = userPetOwnerDao;
    }

    public void createPetOwnerUser(CreateUserDto createUserDto) {
        // Verificamos que el username no exista
        if (userDao.findUserIdByUserName(createUserDto.getUserName()) != null) {
            throw new BarkibuException("SCTY-1002");
        }
        // Verificamos que nombre de usuario no tenga espacios en blanco
        if (ValidationUtil.userNameHasBlankSpaces(createUserDto.getUserName())) {
            throw new BarkibuException("SCTY-1014");
        }
        // Verificamos que el email no exista
        if (userDao.findUserIdByEmail(createUserDto.getEmail()) != null) {
            throw new BarkibuException("SCTY-1003");
        }
        // Verificamos que el email tenga un formato valido
        if (!isEmailValid(createUserDto.getEmail())) {
            throw new BarkibuException("SCTY-1004");
        }
        // Verificamos que ambas contraseñas sean iguales
        if (!createUserDto.getPassword().equals(createUserDto.getConfirmPassword())) {
            throw new BarkibuException("SCTY-1007");
        }
        // Creamos el usuario
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setUserName(createUserDto.getUserName());
        // Encriptamos la contraseña
        String password = BCrypt.withDefaults().hashToString(12, createUserDto.getPassword().toCharArray());
        user.setPassword(password);
        this.userDao.createUser(user);

        // Obtenemos el id del usuario creado
        Integer userId = userDao.findUserIdByUserName(createUserDto.getUserName());
        // Asignamos el grupo de dueño de mascota al usuario recien creado
        this.userPetOwnerDao.addPetOwnerGroup(userId);
    }

    public void updatePetOwnerUser(String userName, UserDto userDto) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if (ValidationUtil.userNameHasBlankSpaces(userDto.getUserName())) {
            throw new BarkibuException("SCTY-1014");
        }
        if (userDao.findUserIdByUserName(userDto.getUserName()) != null && !user.getUserName().equals(userDto.getUserName())) {
            throw new BarkibuException("SCTY-1002");
        }

        if (userDao.findUserIdByEmail(userDto.getEmail()) != null && !user.getEmail().equals(userDto.getEmail())) {
            throw new BarkibuException("SCTY-1003");
        }
        if (!isEmailValid(userDto.getEmail())) {
            throw new BarkibuException("SCTY-1004");
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        this.userDao.updateUser(user);
    }

    public UserDto getPetOwnerUser(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUserName(user.getUserName());
        return userDto;
    }
}
