package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.StateDao;
import bo.edu.ucb.barkibu.dto.StateDto;
import bo.edu.ucb.barkibu.entity.State;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateBl {
    StateDao stateDao;

    public StateBl(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    public List<StateDto> findAllStates() {
        List<State> states = stateDao.findAll();
        List<StateDto> statesDto = new ArrayList<>();
        for (State state : states) {
            statesDto.add(new StateDto(state.getStateId(), state.getState(), state.getCountryId()));
        }
        return statesDto;
    }
}
