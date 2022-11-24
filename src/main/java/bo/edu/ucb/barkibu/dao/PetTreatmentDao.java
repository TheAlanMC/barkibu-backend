package bo.edu.ucb.barkibu.dao;


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
            WHERE pet.pet_id = #{petId}
            AND pet.status = 'activo'
            AND pet_treatment.status = 'activo'
            AND treatment.status = 'activo'
            AND treatment_last_date < now()
            GROUP BY pet.pet_id, treatment.treatment_id, treatment_last_date, treatment_next_date
            ORDER BY treatment_next_date DESC
            """)
    List<PetTreatmentList> findTreatmentLastDateByPetId(Integer petId);

    @Select("""
            SELECT treatment.treatment_id, pet.pet_id, treatment, treatment_last_date, treatment_next_date
            FROM pet_treatment
            JOIN pet ON pet_treatment.pet_id = pet.pet_id
            JOIN treatment ON pet_treatment.treatment_id = treatment.treatment_id
            WHERE pet.pet_id = #{petId}
            AND pet.status = 'activo'
            AND pet_treatment.status = 'activo'
            AND treatment.status = 'activo'
            AND treatment_next_date >= now()
            GROUP BY pet.pet_id, treatment.treatment_id, treatment_last_date, treatment_next_date
            ORDER BY treatment_next_date ASC
            """)
    List<PetTreatmentList> findTreatmentNextDateByPetId(Integer petId);


    @Update("""
            UPDATE pet_treatment
            SET treatment_last_date = #{treatmentLastDate}
            WHERE pet_id = #{petId}
            AND treatment_id = #{treatmentId}
            AND status = 'activo'
            """)
    void updatePetTreatmentDate(PetTreatment petTreatment);
}

