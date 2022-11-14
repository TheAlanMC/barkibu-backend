package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.PetInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface PetInfoDao {

    @Select("""
            SELECT specie, breed, gender, born_date, castrated FROM pet
            JOIN breed ON pet.breed_id = breed.breed_id
            JOIN specie ON breed.specie_id = specie.specie_id
            WHERE pet.pet_id = #{petId}
            AND pet.status = 'activo'
            AND breed.status = 'activo'
            AND specie.status = 'activo';
            """)
    PetInfo findPetInfoByPetId(Integer petId);
}
