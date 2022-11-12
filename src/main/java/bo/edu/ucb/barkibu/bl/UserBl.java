package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.CityDao;
import bo.edu.ucb.barkibu.dao.CountryDao;
import bo.edu.ucb.barkibu.dao.StateDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.CreateUserDto;
import bo.edu.ucb.barkibu.dto.UserVeterianiarnDto;
import bo.edu.ucb.barkibu.entity.City;
import bo.edu.ucb.barkibu.entity.Country;
import bo.edu.ucb.barkibu.entity.State;
import bo.edu.ucb.barkibu.entity.User;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isEmailValid;

@Service
public class UserBl {
    private UserDao userDao;
    private CityDao cityDao;
    private StateDao stateDao;
    private CountryDao countryDao;

    public UserBl(UserDao userDao, CityDao cityDao, StateDao stateDao, CountryDao countryDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.stateDao = stateDao;
        this.countryDao = countryDao;
    }
    public void createUser(CreateUserDto createUserDto) {
        // Verificamos que el username no exista
        if (userDao.findUserIdByUserName(createUserDto.getUserName()) != null) {
            throw new BarkibuException("SCTY-1002", "User name already exists", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que el email no exista
        if (userDao.findUserIdByEmail(createUserDto.getEmail()) != null) {
            throw new BarkibuException("SCTY-1003", "Email already exists", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que el email tenga un formato valido
        if (!isEmailValid(createUserDto.getEmail())) {
            throw new BarkibuException("SCTY-1004", "Email format is invalid", HttpStatus.BAD_REQUEST);
        }
        // Verificamos que ambas contraseñas sean iguales
        if (createUserDto.getPassword().equals(createUserDto.getConfirmPassword())) {
            throw new BarkibuException("SCTY-1007", "Passwords are not equals", HttpStatus.BAD_REQUEST);
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

    public UserVeterianiarnDto findUserVeterinarianByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-3000", "User not found", HttpStatus.NOT_FOUND);
        }
        City city = cityDao.findCityByCityId(user.getCityId());
        if (city == null) {
            throw new BarkibuException("SCTY-3001", "City not found", HttpStatus.NOT_FOUND);
        }
        State state = stateDao.findStateByStateId(city.getStateId());
        if (state == null) {
            throw new BarkibuException("SCTY-3002", "State not found", HttpStatus.NOT_FOUND);
        }
        Country country = countryDao.findCountryByCountryId(state.getCountryId());
        if (country == null) {
            throw new BarkibuException("SCTY-3003", "Country not found", HttpStatus.NOT_FOUND);
        }

        UserVeterianiarnDto userVeterianiarnDto = new UserVeterianiarnDto();
        userVeterianiarnDto.setFirstName(user.getFirstName());
        userVeterianiarnDto.setLastName(user.getLastName());
        userVeterianiarnDto.setCity(city.getCity());
        userVeterianiarnDto.setState(state.getState());
        userVeterianiarnDto.setCountry(country.getCountry());
        userVeterianiarnDto.setDescription(user.getDescription());
        return userVeterianiarnDto;
    }

}
