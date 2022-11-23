package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.CountryDao;
import bo.edu.ucb.barkibu.dto.CategoryDto;
import bo.edu.ucb.barkibu.dto.CountryDto;
import bo.edu.ucb.barkibu.entity.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryBl {
    CountryDao countryDao;

    public CountryBl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<CountryDto> findAllCountries() {
        List<Country> countries = countryDao.findAll();
        List<CountryDto> countriesDto = new ArrayList<>();
        for (Country country : countries) {
            countriesDto.add(new CountryDto(country.getCountryId(), country.getCountry()));
        }
        return countriesDto;
    }
}
