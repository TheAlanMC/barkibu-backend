package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.SpecieDao;
import bo.edu.ucb.barkibu.dto.CategoryDto;
import bo.edu.ucb.barkibu.dto.SpecieDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpecieBl {
    SpecieDao specieDao;

    public SpecieBl(SpecieDao specieDao) {
        this.specieDao = specieDao;
    }

    public List<SpecieDto> findAllSpecies() {
        return specieDao.findAll();
    }


}
