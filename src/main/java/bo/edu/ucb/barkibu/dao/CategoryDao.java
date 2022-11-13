package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.CategoryDto;
import bo.edu.ucb.barkibu.entity.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryDao {

    @Select("""
            SELECT category_id, category
            FROM category
            WHERE status = 'activo'
            """)
    List<CategoryDto> findAll();
}
