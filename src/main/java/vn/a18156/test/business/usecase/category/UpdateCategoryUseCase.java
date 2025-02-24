package vn.a18156.test.business.usecase.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vn.a18156.test.business.usecase.UseCase;
import vn.a18156.test.persistence.service.CategoryService;
import vn.a18156.test.shared.mapper.CategoryMapper;
import vn.a18156.test.shared.model.dto.CategoryDTO;
import vn.a18156.test.shared.model.io.res.ResObject;

@Component
@RequiredArgsConstructor
public class UpdateCategoryUseCase implements UseCase<CategoryDTO, ResObject<CategoryDTO>> {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public ResObject<CategoryDTO> execute(CategoryDTO input) {
        var update = categoryService.update(categoryMapper.toCategory(input));
        return ResObject.<CategoryDTO>builder()
                .status("success")
                .httpStatus(HttpStatus.OK)
                .message("Cập nhật thành công")
                .data(categoryMapper.toDTO(update))
                .build();
    }
}
