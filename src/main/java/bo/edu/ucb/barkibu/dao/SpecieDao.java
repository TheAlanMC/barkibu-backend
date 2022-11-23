package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Specie;
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
    List<Specie> findAll();

    @Select("""
            SELECT specie_id, specie
            FROM specie
            WHERE specie_id = #{specieId}
            AND status = 'activo'
            """)
    Specie findSpecieBySpecieId(Integer specieId);
}
