package bo.edu.ucb.barkibu.dao;

import bo.edu.ucb.barkibu.dto.CategoryDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryDao {

    @Select("""
            SELECT category_id, category
            FROM category
            WHERE status = 'activo'
            ORDER BY category
            """)
    List<CategoryDto> findAll();

    @Select("""
            SELECT category_id, category
            FROM category
            WHERE category_id = #{categoryId}
            AND status = 'activo'
            """)
    CategoryDto findCategoryByCategoryId(Integer categoryId);
}
