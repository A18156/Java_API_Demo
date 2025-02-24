package vn.a18156.test.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.a18156.test.shared.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteByCode(String code);
}
