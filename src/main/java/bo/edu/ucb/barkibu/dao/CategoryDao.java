package bo.edu.ucb.barkibu.dao;

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
            ORDER BY category
            """)
    List<Category> findAll();

    @Select("""
            SELECT category_id, category
            FROM category
            WHERE category_id = #{categoryId}
            AND status = 'activo'
            """)
    Category findCategoryByCategoryId(Integer categoryId);
}
