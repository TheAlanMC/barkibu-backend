package bo.edu.ucb.barkibu.dao;


import bo.edu.ucb.barkibu.dto.PetOwnTreatmentListDto;
import bo.edu.ucb.barkibu.dto.PetTreatmentDto;
import bo.edu.ucb.barkibu.entity.PetTreatment;
import bo.edu.ucb.barkibu.entity.PetTreatmentList;
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
            SELECT treatment.treatment_id, pet.pet_id, treatment, treatment_last_date, treatment_next_date
            FROM pet_treatment
            JOIN pet ON pet_treatment.pet_id = pet.pet_id
            JOIN treatment ON pet_treatment.treatment_id = treatment.treatment_id
            WHERE pet.pet_id = 1
            AND pet.status = 'activo'
            AND pet_treatment.status = 'activo'
            AND treatment.status = 'activo'
            GROUP BY pet.pet_id, treatment.treatment_id, treatment_last_date, treatment_next_date
            """)
    List<PetTreatmentList> findTreatmentByPetId(Integer petId);

    @Update("""
            UPDATE pet_treatment
            SET status = treatment_last_date
            WHERE pet_id = #{petId}
            AND treatment_id = #{treatmentId}
            AND status = 'activo'
            """)
    void UpdatePetTratmentDate(Integer userId);
}

