package vn.a18156.test.persistence.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.a18156.test.persistence.repository.CustomerRepository;
import vn.a18156.test.persistence.service.CustomerService;
import vn.a18156.test.shared.mapper.CustomerMapper;
import vn.a18156.test.shared.model.entity.Customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer create(Customer customer) {
        if(customerRepository.findByPhone(customer.getPhone()).isPresent()) {
            throw new EntityNotFoundException("Số điện thoại này đã tồn tại");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        if(customerRepository.findByPhone(customer.getPhone()).isPresent()) {
            throw new EntityNotFoundException("Số điện thoại này đã tồn tại");
        }
        return customerRepository.findById(customer.getId())
                .map( c -> {
                    c.setName(customer.getName());
                    c.setGender(customer.getGender());
                    c.setAddress(customer.getAddress());
                    c.setPhone(customer.getPhone());
                    return customerRepository.save(c);
                })
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy Id: " + customer.getId()));
    }

    @Override
    public void delete(Long id) {
        if(!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy Id: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
//    @Override
//    public CustomerDTO findByPhone(String phone) {
//        return customerRepository.findByPhone(phone)
//                .map(customerMapper::toDTO)
//                .orElseThrow(()-> new EntityNotFoundException ("không tồn tại số điện thoại: " + phone));
//    }


    @Override
    public Page<Customer> search(String keyword, Pageable pageable) {
        return customerRepository.findAll(buildSearchSpecification(keyword), pageable);
    }

    //kiểm tra dữ liệu tìm kiếm từ input khi tìm kiếm với ngày và giới tính
    private Specification<Customer> buildSearchSpecification(String keyword) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            String likePattern = "%" + keyword + "%";

            predicates.add(cb.like(root.get("name"), likePattern));
            predicates.add(cb.like(root.get("address"), likePattern));
            predicates.add(cb.like(root.get("phone"), likePattern));

            try {
                LocalDateTime dateTime = LocalDateTime.parse(keyword, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                predicates.add(cb.greaterThanOrEqualTo(root.get("create_at"), Timestamp.valueOf(dateTime)));
            } catch (Exception ignored) {}

            if ("true".equalsIgnoreCase(keyword) || "false".equalsIgnoreCase(keyword)) {
                predicates.add(cb.equal(root.get("gender"), Boolean.parseBoolean(keyword)));
            }

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
