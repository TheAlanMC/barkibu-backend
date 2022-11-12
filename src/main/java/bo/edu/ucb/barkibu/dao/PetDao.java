package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Pet;
import org.apache.ibatis.annotations.Insert;
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

}
