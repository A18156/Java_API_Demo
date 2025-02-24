package vn.a18156.test.shared.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vn.a18156.test.shared.model.dto.CategoryDTO;
import vn.a18156.test.shared.model.entity.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toCategory(CategoryDTO dto);
    CategoryDTO toDTO(Category category);
}
