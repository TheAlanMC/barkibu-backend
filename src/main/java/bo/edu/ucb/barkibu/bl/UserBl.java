package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.*;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isEmailValid;
//TODO: SEPARAR EN VARIOS BL (VETERINARIAN, PETOWNER)
@Service
public class UserBl {
    private final UserDao userDao;
    private final CityDao cityDao;
    private final StateDao stateDao;
    private final CountryDao countryDao;

    public UserBl(UserDao userDao, CityDao cityDao, StateDao stateDao, CountryDao countryDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.stateDao = stateDao;
        this.countryDao = countryDao;
    }

    public void createUser(CreateUserDto createUserDto) {
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

    public VeterinarianUserDto getVeterinarianUser(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        City city = cityDao.findCityByCityId(user.getCityId());
        if (city == null) {
            throw new BarkibuException("SCTY-4001");
        }
        State state = stateDao.findStateByStateId(city.getStateId());
        if (state == null) {
            throw new BarkibuException("SCTY-4002");
        }
        Country country = countryDao.findCountryByCountryId(state.getCountryId());
        if (country == null) {
            throw new BarkibuException("SCTY-4003");
        }
        VeterinarianUserDto veterinarianUserDto = new VeterinarianUserDto();
        veterinarianUserDto.setFirstName(user.getFirstName());
        veterinarianUserDto.setLastName(user.getLastName());
        veterinarianUserDto.setCityId(city.getCityId());
        veterinarianUserDto.setStateId(state.getStateId());
        veterinarianUserDto.setCountryId(country.getCountryId());
        veterinarianUserDto.setUserName(user.getUserName());
        veterinarianUserDto.setEmail(user.getEmail());
        veterinarianUserDto.setDescription(user.getDescription());
        veterinarianUserDto.setPhotoPath(user.getPhotoPath());
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
        // TODO: MAYBE VALIDATE COUNTRY, STATE AND CITY?
        user.setFirstName(veterinarianUserDto.getFirstName());
        user.setLastName(veterinarianUserDto.getLastName());
        user.setCityId(veterinarianUserDto.getCityId());
        user.setUserName(veterinarianUserDto.getUserName());
        user.setEmail(veterinarianUserDto.getEmail());
        user.setDescription(veterinarianUserDto.getDescription());
        user.setPhotoPath(veterinarianUserDto.getPhotoPath());
        this.userDao.updateUser(user);
    }

    public void updatePassword(String userName, UpdatePasswordDto updatePasswordDto) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        // Verificamos que la contraseña actual sea correcta
        String currentPasswordInBCrypt = userDao.findPasswordByUserName(userName);
        BCrypt.Result verifyResult = BCrypt.verifyer().verify(updatePasswordDto.getCurrentPassword().toCharArray(), currentPasswordInBCrypt);
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
}
