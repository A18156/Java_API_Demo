package vn.a18156.test.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.a18156.test.shared.model.entity.Customer;

public interface CustomerService {
    Customer create(Customer customer);
    Customer update(Customer customer);
    void delete(Long id);
    Page<Customer> getCustomers(Pageable pageable);
//    CustomerDTO findByPhone(String phone);
    Page<Customer> search(String keyword, Pageable pageable);

}
