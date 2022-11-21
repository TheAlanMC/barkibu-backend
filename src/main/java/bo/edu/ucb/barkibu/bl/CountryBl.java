package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.CountryDao;
import bo.edu.ucb.barkibu.dto.CountryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryBl {
    CountryDao countryDao;

    public CountryBl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<CountryDto> findAllCountries() {
        return countryDao.findAll();
    }
}
