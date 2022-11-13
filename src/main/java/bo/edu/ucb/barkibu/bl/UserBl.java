package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.*;
import bo.edu.ucb.barkibu.dao.HelpedPetDao;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isEmailValid;
//TODO: SEPARAR EN VARIOS BL (VETERINARIAN, PETOWNER)
@Service
public class UserBl {
    private UserDao userDao;
    private CityDao cityDao;
    private StateDao stateDao;
    private CountryDao countryDao;
    private ReputationDao reputationDao;
    private HelpedPetDao helpedPetDao;
    private VeterinaryDao veterinaryDao;

    public UserBl(UserDao userDao, CityDao cityDao, StateDao stateDao, CountryDao countryDao, ReputationDao reputationDao, HelpedPetDao helpedPetDao, VeterinaryDao veterinaryDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.stateDao = stateDao;
        this.countryDao = countryDao;
        this.reputationDao = reputationDao;
        this.helpedPetDao = helpedPetDao;
        this.veterinaryDao = veterinaryDao;
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

    public VeterinarianDto findUserVeterinarianByUserName(String userName) {
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

        VeterinarianDto veterinarianDto = new VeterinarianDto();
        veterinarianDto.setFirstName(user.getFirstName());
        veterinarianDto.setLastName(user.getLastName());
        veterinarianDto.setCity(city.getCity());
        veterinarianDto.setState(state.getState());
        veterinarianDto.setCountry(country.getCountry());
        veterinarianDto.setDescription(user.getDescription());
        return veterinarianDto;
    }

    public VeterinarianRankingDto findVeterinarianRankingByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-3000", "User not found", HttpStatus.NOT_FOUND);
        }
        VeterinarianRankingDto rankingDto = new VeterinarianRankingDto();
        rankingDto.setMonthlyRanking(userDao.findMonthlyRankingByUserName(userName));
        rankingDto.setGeneralRanking(userDao.findGeneralRankingByUserName(userName));
        return rankingDto;
    }

    public Reputation findReputationByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-3000", "User not found", HttpStatus.NOT_FOUND);
        }
        return reputationDao.findReputationByUserName(userName);
    }

    public List<HelpedPet> findHelpedPetByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-3000", "User not found", HttpStatus.NOT_FOUND);
        }
        return helpedPetDao.findHelpedPetByUserName(userName);
    }

    public VeterinaryDto findVeterinaryByUserName(String userName){
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-3000", "User not found", HttpStatus.NOT_FOUND);
        }
        Veterinary veterinary = veterinaryDao.findVeterinaryByUserName(userName);
        if (veterinary == null) {
            throw new BarkibuException("SCTY-3004", "Veterinary not found", HttpStatus.NOT_FOUND);
        }
        VeterinaryDto veterinaryDto = new VeterinaryDto();
        veterinaryDto.setName(veterinary.getName());
        veterinaryDto.setAddress(veterinary.getAddress());
        veterinaryDto.setLatitude(veterinary.getLatitude());
        veterinaryDto.setLongitude(veterinary.getLongitude());
        veterinaryDto.setDescription(veterinary.getDescription());
        return veterinaryDto;
    }

    public VeterinarianProfileDto getVeterinarianProfile(String userName) {
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
        VeterinarianProfileDto veterinarianProfileDto = new VeterinarianProfileDto();
        veterinarianProfileDto.setFirstName(user.getFirstName());
        veterinarianProfileDto.setLastName(user.getLastName());
        veterinarianProfileDto.setCityId(city.getCityId());
        veterinarianProfileDto.setStateId(state.getStateId());
        veterinarianProfileDto.setCountryId(country.getCountryId());
        veterinarianProfileDto.setUserName(user.getUserName());
        veterinarianProfileDto.setEmail(user.getEmail());
        veterinarianProfileDto.setDescription(user.getDescription());
        return veterinarianProfileDto;
    }

    public void updateVeterinarianProfile(String userName, VeterinarianProfileDto veterinarianProfileDto) {
        User user = userDao.findUserByUserName(veterinarianProfileDto.getUserName());
        if (user == null) {
            throw new BarkibuException("SCTY-3000", "User not found", HttpStatus.NOT_FOUND);
        }
        if (userDao.findUserIdByUserName(veterinarianProfileDto.getUserName()) != null && !user.getUserName().equals(veterinarianProfileDto.getUserName())) {
            throw new BarkibuException("SCTY-1002", "User name already exists", HttpStatus.BAD_REQUEST);
        }
        if (userDao.findUserIdByEmail(veterinarianProfileDto.getEmail()) != null && !user.getEmail().equals(veterinarianProfileDto.getEmail())) {
            throw new BarkibuException("SCTY-1003", "Email already exists", HttpStatus.BAD_REQUEST);
        }
        if (!isEmailValid(veterinarianProfileDto.getEmail())) {
            throw new BarkibuException("SCTY-1004", "Email format is invalid", HttpStatus.BAD_REQUEST);
        }
        // TODO: MAYBE VALIDATE COUNTRY, STATE AND CITY?
        user.setFirstName(veterinarianProfileDto.getFirstName());
        user.setLastName(veterinarianProfileDto.getLastName());
        user.setCityId(veterinarianProfileDto.getCityId());
        user.setUserName(veterinarianProfileDto.getUserName());
        user.setEmail(veterinarianProfileDto.getEmail());
        user.setDescription(veterinarianProfileDto.getDescription());
        user.setPhotoPath(veterinarianProfileDto.getPhotoPath());
        userDao.updateUser(user);
    }
}
