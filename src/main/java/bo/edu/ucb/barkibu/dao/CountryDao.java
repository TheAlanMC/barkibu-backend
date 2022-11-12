package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.City;
import bo.edu.ucb.barkibu.entity.Country;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface CountryDao {
    @Select("""
            SELECT country
            FROM country
            WHERE country_id = #{countryId}
            AND status = 'activo'
            """)
    Country findCountryByCountryId(Integer countryId);
}
