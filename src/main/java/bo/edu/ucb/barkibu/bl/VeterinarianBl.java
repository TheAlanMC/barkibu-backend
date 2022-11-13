package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.*;
import bo.edu.ucb.barkibu.dto.VeterinarianInfoDto;
import bo.edu.ucb.barkibu.entity.*;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarianBl {
    private final UserDao userDao;
    private final CityDao cityDao;
    private final StateDao stateDao;
    private final CountryDao countryDao;
    private final VeterinarianRankingDao veterinarianRankingDao;
    private final ReputationDao reputationDao;
    private final HelpedPetDao helpedPetDao;
    private final VeterinarianAnswerDao veterinarianAnswerDao;

    public VeterinarianBl(UserDao userDao, CityDao cityDao, StateDao stateDao, CountryDao countryDao, VeterinarianRankingDao veterinarianRankingDao, ReputationDao reputationDao, HelpedPetDao helpedPetDao, VeterinarianAnswerDao veterinarianAnswerDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.stateDao = stateDao;
        this.countryDao = countryDao;
        this.veterinarianRankingDao = veterinarianRankingDao;
        this.reputationDao = reputationDao;
        this.helpedPetDao = helpedPetDao;
        this.veterinarianAnswerDao = veterinarianAnswerDao;
    }


    public VeterinarianInfoDto findUserVeterinarianByUserName(String userName) {
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
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        VeterinarianRanking veterinarianRanking = new VeterinarianRanking();
        veterinarianRanking.setMonthlyRanking(veterinarianRankingDao.findMonthlyRankingByUserName(userName));
        veterinarianRanking.setGeneralRanking(veterinarianRankingDao.findGeneralRankingByUserName(userName));
        return veterinarianRanking;
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

    public List<VeterinarianAnswer> getVeterinarianAnswers(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        return veterinarianAnswerDao.findVeterinarianAnswersByUserName(userName);
    }

}
