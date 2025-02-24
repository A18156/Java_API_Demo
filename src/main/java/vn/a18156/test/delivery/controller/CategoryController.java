package vn.a18156.test.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.a18156.test.business.usecase.category.CreateCategoryUseCase;
import vn.a18156.test.shared.model.dto.CategoryDTO;
import vn.a18156.test.shared.model.io.res.ResObject;

@RestController
@RequiredArgsConstructor
@CrossOrigin()
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping("/create")
    public ResponseEntity<ResObject<CategoryDTO>> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        var category = createCategoryUseCase.execute(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
