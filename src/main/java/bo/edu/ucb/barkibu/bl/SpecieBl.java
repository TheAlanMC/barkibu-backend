package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.SpecieDao;
import bo.edu.ucb.barkibu.dto.SpecieDto;
import bo.edu.ucb.barkibu.entity.Specie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecieBl {
    SpecieDao specieDao;

    public SpecieBl(SpecieDao specieDao) {
        this.specieDao = specieDao;
    }

    public List<SpecieDto> findAllSpecies() {
        List<Specie> species = specieDao.findAll();
        List<SpecieDto> speciesDto = new ArrayList<>();
        for (Specie specie : species) {
            speciesDto.add(new SpecieDto(specie.getSpecieId(), specie.getSpecie()));
        }
        return speciesDto;
    }


}
