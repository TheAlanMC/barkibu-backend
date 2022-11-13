package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.*;
import bo.edu.ucb.barkibu.dao.HelpedPetDao;
import bo.edu.ucb.barkibu.dto.*;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.entity.VeterinarianAnswer;
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
    private VeterinarianAnswerDao veterinarianAnswerDao;

    public UserBl(UserDao userDao, CityDao cityDao, StateDao stateDao, CountryDao countryDao, ReputationDao reputationDao, HelpedPetDao helpedPetDao, VeterinaryDao veterinaryDao, VeterinarianAnswerDao veterinarianAnswerDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.stateDao = stateDao;
        this.countryDao = countryDao;
        this.reputationDao = reputationDao;
        this.helpedPetDao = helpedPetDao;
        this.veterinaryDao = veterinaryDao;
        this.veterinarianAnswerDao = veterinarianAnswerDao;
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

    public VeterinarianDto findUserVeterinarianByUserName(String userName) {
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

        VeterinarianDto veterinarianDto = new VeterinarianDto();
        veterinarianDto.setFirstName(user.getFirstName());
        veterinarianDto.setLastName(user.getLastName());
        veterinarianDto.setCity(city.getCity());
        veterinarianDto.setState(state.getState());
        veterinarianDto.setCountry(country.getCountry());
        veterinarianDto.setDescription(user.getDescription());
        veterinarianDto.setPhotoPath(user.getPhotoPath());
        return veterinarianDto;
    }

    public VeterinarianRankingDto findVeterinarianRankingByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        VeterinarianRankingDto rankingDto = new VeterinarianRankingDto();
        rankingDto.setMonthlyRanking(userDao.findMonthlyRankingByUserName(userName));
        rankingDto.setGeneralRanking(userDao.findGeneralRankingByUserName(userName));
        return rankingDto;
    }

    public Reputation findReputationByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        return reputationDao.findReputationByUserName(userName);
    }

    public List<HelpedPet> findHelpedPetByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        return helpedPetDao.findHelpedPetByUserName(userName);
    }

    public VeterinaryDto findVeterinaryByUserName(String userName){
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        Veterinary veterinary = veterinaryDao.findVeterinaryByUserName(userName);
        if (veterinary == null) {
            throw new BarkibuException("SCTY-4004");
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
        VeterinarianProfileDto veterinarianProfileDto = new VeterinarianProfileDto();
        veterinarianProfileDto.setFirstName(user.getFirstName());
        veterinarianProfileDto.setLastName(user.getLastName());
        veterinarianProfileDto.setCityId(city.getCityId());
        veterinarianProfileDto.setStateId(state.getStateId());
        veterinarianProfileDto.setCountryId(country.getCountryId());
        veterinarianProfileDto.setUserName(user.getUserName());
        veterinarianProfileDto.setEmail(user.getEmail());
        veterinarianProfileDto.setDescription(user.getDescription());
        veterinarianProfileDto.setPhotoPath(user.getPhotoPath());
        return veterinarianProfileDto;
    }

    public void updateVeterinarianProfile(String userName, VeterinarianProfileDto veterinarianProfileDto) {
        User user = userDao.findUserByUserName(veterinarianProfileDto.getUserName());
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if (userDao.findUserIdByUserName(veterinarianProfileDto.getUserName()) != null && !user.getUserName().equals(veterinarianProfileDto.getUserName())) {
            throw new BarkibuException("SCTY-1002");
        }
        if (userDao.findUserIdByEmail(veterinarianProfileDto.getEmail()) != null && !user.getEmail().equals(veterinarianProfileDto.getEmail())) {
            throw new BarkibuException("SCTY-1003");
        }
        if (!isEmailValid(veterinarianProfileDto.getEmail())) {
            throw new BarkibuException("SCTY-1004");
        }
        // TODO: MAYBE VALIDATE COUNTRY, STATE AND CITY?
        user.setFirstName(veterinarianProfileDto.getFirstName());
        user.setLastName(veterinarianProfileDto.getLastName());
        user.setCityId(veterinarianProfileDto.getCityId());
        user.setUserName(veterinarianProfileDto.getUserName());
        user.setEmail(veterinarianProfileDto.getEmail());
        user.setDescription(veterinarianProfileDto.getDescription());
        user.setPhotoPath(veterinarianProfileDto.getPhotoPath());
        this.userDao.updateUser(user);
    }

    //todo: move to veterinary business logic
    public void createVeterinary(String userName, VeterinaryDto veterinaryDto) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if (veterinaryDao.findVeterinaryByUserName(userName) != null) {
            throw new BarkibuException("SCTY-1009");
        }
        Veterinary veterinary = new Veterinary();
        veterinary.setUserId(user.getUserId());
        veterinary.setName(veterinaryDto.getName());
        veterinary.setAddress(veterinaryDto.getAddress());
        veterinary.setLatitude(veterinaryDto.getLatitude());
        veterinary.setLongitude(veterinaryDto.getLongitude());
        veterinary.setDescription(veterinaryDto.getDescription());
        this.veterinaryDao.createVeterinary(veterinary);
    }

    public void updateVeterinary(String userName, VeterinaryDto veterinaryDto) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        Veterinary veterinary = veterinaryDao.findVeterinaryByUserName(userName);
        if (veterinary == null) {
            throw new BarkibuException("SCTY-4004");
        }
        veterinary.setUserId(user.getUserId());
        veterinary.setName(veterinaryDto.getName());
        veterinary.setAddress(veterinaryDto.getAddress());
        veterinary.setLatitude(veterinaryDto.getLatitude());
        veterinary.setLongitude(veterinaryDto.getLongitude());
        veterinary.setDescription(veterinaryDto.getDescription());
        this.veterinaryDao.updateVeterinary(veterinary);
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

    public List<VeterinarianAnswer> getVeterinarianAnswers(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        List<VeterinarianAnswer> veterinarianAnswers = veterinarianAnswerDao.findVeterinarianAnswersByUserName(userName);
        return veterinarianAnswers;
    }
}
