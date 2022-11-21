package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.CityDao;
import bo.edu.ucb.barkibu.dao.StateDao;
import bo.edu.ucb.barkibu.dto.CityDto;
import bo.edu.ucb.barkibu.dto.StateDto;
import bo.edu.ucb.barkibu.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateBl {
    StateDao stateDao;

    public StateBl(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    public List<StateDto> findAllStates() {
        return stateDao.findAll();
    }
}
