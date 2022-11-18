package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Pet;
import bo.edu.ucb.barkibu.entity.PetInfo;
import bo.edu.ucb.barkibu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

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
            SELECT user_id, city_id, first_name, last_name, email, user_name, photo_path, description
            FROM "user"
            WHERE user_name = #{userName}
            AND status = 'activo'
            """)
    User findPetByPetName(String userName);
    //Actualiza datos de la mascota
    @Update("""
            UPDATE pet
            SET name = #{name}, gender = #{gender}, castrated = #{castrated},
                born_date = #{born_date}, breed_id = #{breedId}, chip_number = #{chip_number}
            WHERE pet_id = #{petId}
            AND status = 'activo'
            """)
    void updatePet(Pet pet);

}
