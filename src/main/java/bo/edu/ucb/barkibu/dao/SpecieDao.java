package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.SpecieDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface SpecieDao {
    @Select("""
            SELECT specie_id, specie
            FROM specie
            WHERE status = 'activo'
            """)
    List<SpecieDto> findAll();
}
