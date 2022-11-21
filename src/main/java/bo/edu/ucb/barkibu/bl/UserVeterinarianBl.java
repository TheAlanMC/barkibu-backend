package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.*;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.VeterinarianUserDto;
import bo.edu.ucb.barkibu.entity.City;
import bo.edu.ucb.barkibu.entity.Country;
import bo.edu.ucb.barkibu.entity.State;
import bo.edu.ucb.barkibu.entity.User;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isEmailValid;

@Service
public class UserVeterinarianBl {
    private final UserDao userDao;
    private final UserVeterinarianDao userVeterinarianDao;


    public UserVeterinarianBl(UserDao userDao, UserVeterinarianDao userVeterinarianDao) {
        this.userDao = userDao;
        this.userVeterinarianDao = userVeterinarianDao;
    }

    public void createVeterinarianUser(CreateUserDto createUserDto) {
        // Verificamos que el username no exista
        if (userDao.findUserIdByUserName(createUserDto.getUserName()) != null) {
            throw new BarkibuException("SCTY-1002");
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
        // Asignamos el grupo de veterinario al usuario recien creado
        this.userVeterinarianDao.addVeterinarianGroup(userId);
    }

    public VeterinarianUserDto getVeterinarianUser(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        VeterinarianUserDto veterinarianUserDto = userDao.findVeterinarianUserByUserName(userName);
        return veterinarianUserDto;
    }

    public void updateVeterinarianProfile(String userName, VeterinarianUserDto veterinarianUserDto) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if (userDao.findUserIdByUserName(veterinarianUserDto.getUserName()) != null && !user.getUserName().equals(veterinarianUserDto.getUserName())) {
            throw new BarkibuException("SCTY-1002");
        }
        if (userDao.findUserIdByEmail(veterinarianUserDto.getEmail()) != null && !user.getEmail().equals(veterinarianUserDto.getEmail())) {
            throw new BarkibuException("SCTY-1003");
        }
        if (!isEmailValid(veterinarianUserDto.getEmail())) {
            throw new BarkibuException("SCTY-1004");
        }
        user.setFirstName(veterinarianUserDto.getFirstName());
        user.setLastName(veterinarianUserDto.getLastName());
        user.setCityId(veterinarianUserDto.getCityId());
        user.setUserName(veterinarianUserDto.getUserName());
        user.setEmail(veterinarianUserDto.getEmail());
        user.setDescription(veterinarianUserDto.getDescription());
        user.setPhotoPath(veterinarianUserDto.getPhotoPath());
        this.userVeterinarianDao.updateUser(user);
    }
}
