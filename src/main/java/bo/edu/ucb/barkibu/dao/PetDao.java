package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Pet;
import bo.edu.ucb.barkibu.entity.PetData;
import bo.edu.ucb.barkibu.entity.PetInfo;
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

    @Select("""
            SELECT pet_id, name, breed_id, gender, castrated, born_date, chip_number
            FROM pet
            WHERE pet_id = #{PetId}
            AND pet.status = 'activo'
            """)
    Pet findPetByPetId(Integer petId);

    @Select("""
            SELECT pet.pet_id, pet.name, specie, breed, pet.photo_path, born_date, chip_number, gender, castrated
            FROM pet
            JOIN breed ON pet.breed_id = breed.breed_id
            JOIN specie ON breed.specie_id = specie.specie_id
            WHERE pet.pet_id = #{petId}
            AND pet.status = 'activo'
            AND breed.status = 'activo'
            AND specie.status = 'activo'
            ORDER BY pet.pet_id
            """)
    PetInfo findPetInfoByPetId(Integer petId);
    //Actualiza datos de la mascota
    @Update("""
            UPDATE pet
            SET name = #{name}, gender = #{gender}, castrated = #{castrated},
            born_date = #{bornDate}, breed_id = #{breedId}, chip_number = #{chipNumber}
            WHERE pet_id = #{petId}
            AND status = 'activo'
            """)
    void updatePet(Pet pet);

    //Listado de mascotas por nombre de usuario
    @Select("""		
            SELECT pet.pet_id, pet.name, specie, breed, pet.photo_path, born_date, chip_number, gender, castrated
            FROM pet
            JOIN breed ON pet.breed_id = breed.breed_id
            JOIN specie ON breed.specie_id = specie.specie_id
            JOIN "user" ON pet.user_id = "user".user_id
            WHERE "user".user_name= #{userName}
            AND pet.status = 'activo'
            AND breed.status = 'activo'
            AND specie.status = 'activo'
            AND "user".status = 'activo'
            ORDER BY pet.pet_id
            """)
    List<PetInfo>findPetInfoByUserName(String userName);

    //Informacíon de la pantalla perfil mascota
    @Select("""
            SELECT pet.name,gender, pet.breed_id, specie.specie_id, castrated, born_date, chip_number, pet.photo_path from pet
            JOIN breed ON pet.breed_id = breed.breed_id
            JOIN specie ON breed.specie_id = specie.specie_id
            WHERE pet.pet_id= #{petId}
            AND pet.status = 'activo'
            AND breed.status = 'activo'
            AND specie.status = 'activo'
            """)
    PetData findPetDataByPetId(Integer petId);
    //Eliminación de mascota
    @Update("""
            UPDATE pet
                        SET status = 'inactivo'
                        WHERE pet_id= #{petId}
            			AND status = 'activo'
            """)
    void deletePet(Pet pet);
}

