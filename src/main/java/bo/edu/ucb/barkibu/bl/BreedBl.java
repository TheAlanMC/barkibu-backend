package bo.edu.ucb.barkibu.bl;


import bo.edu.ucb.barkibu.dao.BreedDao;
import bo.edu.ucb.barkibu.dao.SpecieDao;
import bo.edu.ucb.barkibu.dto.BreedDto;
import bo.edu.ucb.barkibu.entity.Breed;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedBl {
    BreedDao breedDao;
    SpecieDao specieDao;

    public BreedBl(BreedDao breedDao, SpecieDao specieDao) {
        this.breedDao = breedDao;
        this.specieDao = specieDao;
    }

    public List<BreedDto> findAllBreeds() {
        List<Breed> breeds =  breedDao.findAll();
        List<BreedDto> breedDtos = new ArrayList<>();
        for (Breed breed : breeds) {
            breedDtos.add(new BreedDto(breed.getBreedId(), breed.getSpecieId(), breed.getBreed()));
        }
        return breedDtos;
    }
}
