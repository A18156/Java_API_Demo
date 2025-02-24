package vn.a18156.test.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.a18156.test.shared.model.entity.OrderDetail;

public interface OrderDetailReopsitory extends JpaRepository<OrderDetail, Long> {
}
