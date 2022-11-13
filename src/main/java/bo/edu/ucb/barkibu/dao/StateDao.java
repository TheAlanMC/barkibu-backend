package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.State;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface StateDao {
    @Select("""
            SELECT state_id, state, country_id
            FROM state
            WHERE state_id = #{stateId}
            AND status = 'activo'
            """)
    State findStateByStateId(Integer stateId);
}
