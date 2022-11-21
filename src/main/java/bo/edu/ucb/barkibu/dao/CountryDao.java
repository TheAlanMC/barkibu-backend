package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.CountryDto;
import bo.edu.ucb.barkibu.entity.Country;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CountryDao {
    @Select("""
            SELECT country_id, country
            FROM country
            WHERE status = 'activo'
            """)
    List<CountryDto> findAll();
}
