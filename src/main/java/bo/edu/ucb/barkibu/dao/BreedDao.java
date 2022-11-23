package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Breed;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BreedDao {
    @Select("""
            SELECT breed_id, breed
            FROM breed
            WHERE status = 'activo'
            AND specie_id = #{specieId}
            """)
    List<Breed> findBreedBySpecieId(Integer specieId);
}
