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
    private final VeterinarianInfoDao veterinarianInfoDao;

    public VeterinarianInfoBl(UserDao userDao, VeterinarianInfoDao veterinarianInfoDao) {
        this.userDao = userDao;
        this.veterinarianInfoDao = veterinarianInfoDao;
    }

    public VeterinarianInfoDto findUserVeterinarianByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (user == null) {
            throw new BarkibuException("SCTY-4000");
        }
        return veterinarianInfoDao.findVeterinarianInfoByUserName(userName);
    }

    public VeterinarianRanking findVeterinarianRankingByUserName(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        VeterinarianRanking veterinarianRanking = new VeterinarianRanking();
        veterinarianRanking.setMonthlyRanking(veterinarianInfoDao.findMonthlyRankingByUserName(userName));
        veterinarianRanking.setGeneralRanking(veterinarianInfoDao.findGeneralRankingByUserName(userName));
        return veterinarianRanking;
    }

    public Reputation findReputationByUserName(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        return veterinarianInfoDao.findReputationByUserName(userName);
    }

    public List<HelpedPet> findHelpedPetByUserName(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        return veterinarianInfoDao.findHelpedPetByUserName(userName);
    }

    public List<VeterinarianOwnAnswer> getVeterinarianAnswers(String userName) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        return veterinarianInfoDao.findVeterinarianAnswersByUserName(userName);
    }

}
