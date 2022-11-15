package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dao.VeterinaryDao;
import bo.edu.ucb.barkibu.dto.VeterinaryDto;
import bo.edu.ucb.barkibu.entity.Veterinary;
import bo.edu.ucb.barkibu.util.BarkibuException;
import org.springframework.stereotype.Service;

@Service
public class VeterinaryBl {
    private final UserDao userDao;
    private final VeterinaryDao veterinaryDao;

    public VeterinaryBl(UserDao userDao, VeterinaryDao veterinaryDao) {
        this.userDao = userDao;
        this.veterinaryDao = veterinaryDao;
    }

    public void createVeterinary(String userName, VeterinaryDto veterinaryDto) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        if (veterinaryDao.findVeterinaryByUserName(userName) != null) {
            throw new BarkibuException("SCTY-1009");
        }
        Veterinary veterinary = new Veterinary();
        veterinary.setUserId(userDao.findUserIdByUserName(userName));
        veterinary.setName(veterinaryDto.getName());
        veterinary.setAddress(veterinaryDto.getAddress());
        veterinary.setLatitude(veterinaryDto.getLatitude());
        veterinary.setLongitude(veterinaryDto.getLongitude());
        veterinary.setDescription(veterinaryDto.getDescription());
        this.veterinaryDao.createVeterinary(veterinary);
    }

    public VeterinaryDto findVeterinaryByUserName(String userName){
        if (userDao.findUserIdByUserName(userName) == null) {
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

    public void updateVeterinary(String userName, VeterinaryDto veterinaryDto) {
        if (userDao.findUserIdByUserName(userName) == null) {
            throw new BarkibuException("SCTY-4000");
        }
        Veterinary veterinary = veterinaryDao.findVeterinaryByUserName(userName);
        if (veterinary == null) {
            throw new BarkibuException("SCTY-4004");
        }
        veterinary.setUserId(userDao.findUserIdByUserName(userName));
        veterinary.setName(veterinaryDto.getName());
        veterinary.setAddress(veterinaryDto.getAddress());
        veterinary.setLatitude(veterinaryDto.getLatitude());
        veterinary.setLongitude(veterinaryDto.getLongitude());
        veterinary.setDescription(veterinaryDto.getDescription());
        this.veterinaryDao.updateVeterinary(veterinary);
    }
}
