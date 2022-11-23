package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.CityDao;
import bo.edu.ucb.barkibu.dto.CityDto;
import bo.edu.ucb.barkibu.entity.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityBl {
    CityDao cityDao;

    public CityBl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public List<CityDto> findAllCities() {
        List<City> cities = cityDao.findAll();
        List<CityDto> citiesDto = new ArrayList<>();
        for (City city : cities) {
            citiesDto.add(new CityDto(city.getCityId(), city.getCity(), city.getStateId()));
        }
        return citiesDto;
    }
}
