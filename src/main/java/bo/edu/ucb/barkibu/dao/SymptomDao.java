package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.entity.Category;
import bo.edu.ucb.barkibu.entity.Symptom;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SymptomDao {

    @Select("""
            SELECT symptom_id, symptom
            FROM symptom
            WHERE status = 'activo'
            ORDER BY symptom
            """)
    List<Symptom> findAll();

    @Select("""
            SELECT symptom_id, symptom
            FROM symptom
            WHERE symptom_id = #{categoryId}
            AND status = 'activo'
            """)
    Symptom findSymptomBySymptomId(Integer symptomId);
}
