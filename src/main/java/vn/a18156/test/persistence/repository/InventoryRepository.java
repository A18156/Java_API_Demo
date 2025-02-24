package vn.a18156.test.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.a18156.test.shared.model.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
