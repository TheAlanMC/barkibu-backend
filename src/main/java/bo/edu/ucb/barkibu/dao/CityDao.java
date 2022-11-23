package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.City;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CityDao {
    @Select("""
            SELECT city_id, city, state_id
            FROM city
            WHERE status = 'activo'
            ORDER BY city
            """)
    List<City> findAll();
}
