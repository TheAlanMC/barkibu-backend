package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Country;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
// TODO: RETURN ENTITY INSTED OF DTO
public interface CountryDao {
    @Select("""
            SELECT country_id, country
            FROM country
            WHERE status = 'activo'
            ORDER BY country
            """)
    List<Country> findAll();
}
