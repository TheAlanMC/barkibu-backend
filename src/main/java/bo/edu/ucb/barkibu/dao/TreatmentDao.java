package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Treatment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TreatmentDao {

    @Select("""
            SELECT treatment_id, treatment, description
            FROM treatment
            WHERE status = 'activo'
            ORDER BY treatment
            """)
    List<Treatment> findAll();



}
