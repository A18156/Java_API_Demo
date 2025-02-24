package vn.a18156.test.business.usecase.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vn.a18156.test.business.usecase.UseCase;
import vn.a18156.test.persistence.service.CategoryService;
import vn.a18156.test.shared.model.io.res.ResObject;

@Component
@RequiredArgsConstructor
public class DeleteCategoryUseCase implements UseCase<String, ResObject<Void>> {
    private final CategoryService categoryService;
    @Override
    public ResObject<Void> execute(String input) {
        categoryService.delete(input);
        return ResObject.<Void>builder()
                .status("success")
                .httpStatus(HttpStatus.NO_CONTENT)
                .message("Xoá thành công")
                .data(null)
                .build();
    }
}
