package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.PetTreatment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PetTreatmentDao {
    // Registro de tratamiento (vacuna, desparasitación, etc) de mascota
    @Insert("""
            INSERT INTO pet_treatment
            (treatment_id, pet_id, treatment_last_date, treatment_next_date, status, tx_date, tx_user, tx_host)
            VALUES (#{treatmentId}, #{petId}, #{treatmentLastDate}, #{treatmentNextDate}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createPetTreatment(PetTreatment petTreatment);

    @Select("""
            SELECT pet_treatment.pet_id, treatment_last_date, treatment_next_date
            FROM pet_treatment
            JOIN pet ON pet_treatment.pet_id = pet.pet_id
            WHERE pet.pet_id = #{petId}
            """)
    List<PetTreatment> findTreatmentByPetId(Integer petId);
}

