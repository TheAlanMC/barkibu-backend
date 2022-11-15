package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.SpecieDao;
import bo.edu.ucb.barkibu.dto.SpecieDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecieBl {
    SpecieDao specieDao;

    public SpecieBl(SpecieDao specieDao) {
        this.specieDao = specieDao;
    }

    public List<SpecieDto> findAllSpecies() {
        return specieDao.findAll();
    }


}
