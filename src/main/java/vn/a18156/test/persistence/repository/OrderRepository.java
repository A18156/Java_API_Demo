package vn.a18156.test.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.a18156.test.shared.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
