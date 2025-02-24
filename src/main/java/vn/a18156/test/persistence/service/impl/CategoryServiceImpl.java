package vn.a18156.test.persistence.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.a18156.test.persistence.repository.CategoryRepository;
import vn.a18156.test.persistence.service.CategoryService;
import vn.a18156.test.shared.mapper.CategoryMapper;
import vn.a18156.test.shared.model.dto.CategoryDTO;
import vn.a18156.test.shared.model.entity.Category;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElseThrow(()-> new EntityNotFoundException( "Không tìm thấy Id: " + id));
    }

    @Override
    public Category create(Category category) {
        category.setCode(category.getCode().toUpperCase());
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.findById(category.getId())
                .map(c -> {
                    c.setName(category.getName());
                    return categoryRepository.save(c);
                })
                .orElseThrow(()-> new EntityNotFoundException("Không tìm thấy Id: " + category.getId()));
    }

    @Override
    public void delete(String code){
        categoryRepository.deleteByCode(code);
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
