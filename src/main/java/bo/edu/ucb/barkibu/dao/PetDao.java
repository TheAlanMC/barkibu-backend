package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Pet;
import bo.edu.ucb.barkibu.entity.PetInfo;
import bo.edu.ucb.barkibu.entity.PetInfoId;
import bo.edu.ucb.barkibu.entity.QuestionOwner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PetDao {
    // Registro de mascota
    @Insert("""
            INSERT INTO pet
            (user_id, breed_id, name, gender, castrated, born_date, photo_path, chip_number, status, tx_date, tx_user, tx_host)
            VALUES (#{userId}, #{breedId}, #{name}, #{gender}, #{castrated}, #{bornDate}, #{photoPath}, #{chipNumber}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createPet(Pet pet);
    // Muestra datos de la mascota
    @Select("""
            SELECT name,  born_date, chip_number FROM pet
            WHERE pet_id = #{petId}
            AND pet.status = 'activo'
            """)
    Pet findPetInfoByPetId(Integer petId);
    @Select("""
            SELECT name, breed_id, gender, castrated, born_date, chip_number
            FROM pet
            WHERE pet_id = #{PetId}
            AND pet.status = 'activo'
            """)
    Pet findPetByPetName(Integer PetId);

    @Select("""
            SELECT name, breed_id, gender, castrated, born_date, chip_number
            FROM pet
            WHERE name = #{petId}
            AND status = 'activo'
            """)
    Pet findPetByPetId(Integer userName);
    //Actualiza datos de la mascota
    @Update("""
            UPDATE pet
            SET name = #{name}, gender = #{gender}, castrated = #{castrated},
                born_date = #{bornDate}, breed_id = #{breedId}, chip_number = #{chipNumber}
            WHERE pet_id = 2
            AND status = 'activo'
            """)
    void updatePet(Pet pet);
    //Información de la mascota por id

    @Select("""
            SELECT pet.name, breed,born_date, chip_number,specie,pet.photo_path from pet
            		JOIN breed ON pet.breed_id = breed.breed_id
                    JOIN specie ON breed.specie_id = specie.specie_id
            		WHERE pet.pet_id= #{petId}
            		AND pet.status = 'activo'
                    AND breed.status = 'activo'
                    AND specie.status = 'activo'
            """)
    PetInfoId findPetInfoById(Integer petId);
    //Listado de mascotas por token usuario
    @Select("""		
            SELECT pet.name, breed,born_date, chip_number,specie,pet.photo_path from pet
            		JOIN breed ON pet.breed_id = breed.breed_id
                    JOIN specie ON breed.specie_id = specie.specie_id
            		JOIN "user" ON pet.user_id = "user".user_id
            		WHERE "user".user_name= #{userName}
            		AND pet.status = 'activo'
                    AND breed.status = 'activo'
                    AND specie.status = 'activo'
                    AND "user".status = 'activo'
                    
                        
            """)
    List<PetInfoId >findPetInfoByToken(String userName);
    //Informacíon de la pantalla perfil mascota
    @Select("""
            SELECT pet.name,gender, pet.breed_id,born_date, castrated,chip_number,pet.photo_path from pet
                        		JOIN breed ON pet.breed_id = breed.breed_id
                                JOIN specie ON breed.specie_id = specie.specie_id
                        		WHERE pet.pet_id= #{petId}
                        		AND pet.status = 'activo'
                                AND breed.status = 'activo'
                                AND specie.status = 'activo'
            """)
    Pet finPetInfo(Integer petId);
}

