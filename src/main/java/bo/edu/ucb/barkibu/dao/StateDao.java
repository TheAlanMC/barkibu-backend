package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.StateDto;
import bo.edu.ucb.barkibu.entity.State;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StateDao {
    @Select("""
            SELECT state_id, state, country_id
            FROM state
            WHERE status = 'activo'
            ORDER BY state
            """)
    List<StateDto> findAll();
}
