package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.PetTreatment;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface PetTreatmentDao {
    // Registro de tratamiento (vacuna, desparasitación, etc) de mascota
    @Insert("""
            INSERT INTO pet_treatment
            (treatment_id, pet_id, treatment_last_date, treatment_next_date, status, tx_date, tx_user, tx_host)
            VALUES (#{treatmentId}, #{petId}, #{treatmentLastDate}, #{treatmentNextDate}, 'activo', now(), 'anonymus', 'localhost')
            """)
    void createPetTreatment(PetTreatment petTreatment);
}
