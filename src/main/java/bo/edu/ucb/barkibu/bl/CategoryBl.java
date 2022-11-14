package bo.edu.ucb.barkibu.bl;

import bo.edu.ucb.barkibu.dao.CategoryDao;
import bo.edu.ucb.barkibu.dto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryBl {
    CategoryDao categoryDao;

    public CategoryBl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<CategoryDto> findAllCategories() {
        return categoryDao.findAll();
    }
}
