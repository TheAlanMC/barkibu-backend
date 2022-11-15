package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.*;
import bo.edu.ucb.barkibu.dto.VeterinarianInfoDto;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarianInfoBl {
    private final UserDao userDao;
    private final CityDao cityDao;
    private final StateDao stateDao;
    private final CountryDao countryDao;
    private final VeterinarianRankingDao veterinarianRankingDao;
    private final ReputationDao reputationDao;
    private final HelpedPetDao helpedPetDao;
    private final VeterinarianOwnAnswerDao veterinarianOwnAnswerDao;

    public VeterinarianInfoBl(UserDao userDao, CityDao cityDao, StateDao stateDao, CountryDao countryDao, VeterinarianRankingDao veterinarianRankingDao, ReputationDao reputationDao, HelpedPetDao helpedPetDao, VeterinarianOwnAnswerDao veterinarianOwnAnswerDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.stateDao = stateDao;
        this.countryDao = countryDao;
        this.veterinarianRankingDao = veterinarianRankingDao;
        this.reputationDao = reputationDao;
        this.helpedPetDao = helpedPetDao;
        this.veterinarianOwnAnswerDao = veterinarianOwnAnswerDao;
    }


    public VeterinarianInfoDto findUserVeterinarianByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        // Verificamos que el usuario sea un veterinario
        if(userDao.findVeterinarianByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4010");
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

        VeterinarianInfoDto veterinarianInfoDto = new VeterinarianInfoDto();
        veterinarianInfoDto.setFirstName(user.getFirstName());
        veterinarianInfoDto.setLastName(user.getLastName());
        veterinarianInfoDto.setCity(city.getCity());
        veterinarianInfoDto.setState(state.getState());
        veterinarianInfoDto.setCountry(country.getCountry());
        veterinarianInfoDto.setDescription(user.getDescription());
        veterinarianInfoDto.setPhotoPath(user.getPhotoPath());
        return veterinarianInfoDto;
    }

    public VeterinarianRanking findVeterinarianRankingByUserName(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if(userDao.findVeterinarianByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4010");
        }
        VeterinarianRanking veterinarianRanking = new VeterinarianRanking();
        veterinarianRanking.setMonthlyRanking(veterinarianRankingDao.findMonthlyRankingByUserName(userName));
        veterinarianRanking.setGeneralRanking(veterinarianRankingDao.findGeneralRankingByUserName(userName));
        return veterinarianRanking;
    }

    public Reputation findReputationByUserName(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if(userDao.findVeterinarianByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4010");
        }
        return reputationDao.findReputationByUserName(userName);
    }

    public List<HelpedPet> findHelpedPetByUserName(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if(userDao.findVeterinarianByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4010");
        }
        return helpedPetDao.findHelpedPetByUserName(userName);
    }

    public List<VeterinarianOwnAnswer> getVeterinarianAnswers(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if(userDao.findVeterinarianByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4010");
        }
        return veterinarianOwnAnswerDao.findVeterinarianAnswersByUserName(userName);
    }

}
