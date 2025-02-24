package vn.a18156.test.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.a18156.test.shared.model.dto.CategoryDTO;
import vn.a18156.test.shared.model.entity.Category;

public interface CategoryService {
    CategoryDTO getById(Long id);
    Category create(Category category);
    Category update(Category category);
    void delete(String code);
    Page<Category> getCategories(Pageable pageable);
}
