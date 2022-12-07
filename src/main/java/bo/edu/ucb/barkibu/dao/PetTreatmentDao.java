package bo.edu.ucb.barkibu.dao;


import bo.edu.ucb.barkibu.entity.PetTreatment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
            SELECT pet_treatment_id, treatment.treatment_id, pet.pet_id, treatment, treatment_last_date, treatment_next_date
            FROM pet_treatment
            JOIN pet ON pet_treatment.pet_id = pet.pet_id
            JOIN treatment ON pet_treatment.treatment_id = treatment.treatment_id
            WHERE pet.pet_id = #{petId}
            AND pet.status = 'activo'
            AND pet_treatment.status = 'activo'
            AND treatment.status = 'activo'
            AND treatment_next_date < now()
            GROUP BY pet_treatment_id, pet.pet_id, treatment.treatment_id, treatment_last_date, treatment_next_date
            ORDER BY treatment_next_date DESC
            """)
    List<PetTreatment> findTreatmentLastDateByPetId(Integer petId);

    @Select("""
            SELECT pet_treatment_id, treatment.treatment_id, pet.pet_id, treatment, treatment_last_date, treatment_next_date
            FROM pet_treatment
            JOIN pet ON pet_treatment.pet_id = pet.pet_id
            JOIN treatment ON pet_treatment.treatment_id = treatment.treatment_id
            WHERE pet.pet_id = #{petId}
            AND pet.status = 'activo'
            AND pet_treatment.status = 'activo'
            AND treatment.status = 'activo'
            AND treatment_next_date >= now()
            GROUP BY pet_treatment_id, pet.pet_id, treatment.treatment_id, treatment_last_date, treatment_next_date
            ORDER BY treatment_next_date ASC
            """)
    List<PetTreatment> findTreatmentNextDateByPetId(Integer petId);


    @Update("""
            UPDATE pet_treatment
            SET treatment_last_date = #{treatmentLastDate}
            WHERE pet_id = #{petId}
            AND treatment_id = #{treatmentId}
            AND status = 'activo'
            """)
    void updatePetTreatmentDate(PetTreatment petTreatment);

    @Select("""
            SELECT pet_treatment_id
            FROM pet_treatment
            WHERE pet_treatment_id = #{petTreatmentId}
            AND status = 'activo'
            """)
    Integer findPetTreatmentIdByPetTreatmentId(Integer petTreatmentId);

    @Update("""
            UPDATE pet_treatment
            SET status = 'inactivo'
            WHERE pet_treatment_id = #{petTreatmentId}
            AND status = 'activo'
            """)
    void deletePetTreatment(Integer petTreatmentId);
}

