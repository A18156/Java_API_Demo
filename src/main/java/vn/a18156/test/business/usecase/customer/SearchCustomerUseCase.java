package vn.a18156.test.business.usecase.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vn.a18156.test.business.usecase.SearchUseCase;
import vn.a18156.test.persistence.service.CustomerService;
import vn.a18156.test.shared.mapper.CustomerMapper;
import vn.a18156.test.shared.model.dto.CustomerDTO;
import vn.a18156.test.shared.model.entity.Customer;
import vn.a18156.test.shared.model.io.res.ResPage;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchCustomerUseCase implements SearchUseCase<String, Pageable, ResPage<CustomerDTO>> {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Override
    public ResPage<CustomerDTO> execute(String keyword, Pageable pageable) {
        Page<Customer> customers = customerService.search(keyword, pageable);

        if (pageable.getPageNumber() > (customers.getTotalPages() - 1)) {
            throw new IllegalArgumentException("Số trang tối đa " + customers.getTotalPages());
        }

        return ResPage.<CustomerDTO>builder()
                .data(customers.getContent().stream().map(customerMapper::toDTO).collect(Collectors.toList()))
                .totalPages(customers.getTotalPages())
                .totalElements(customers.getTotalElements())
                .size(customers.getSize())
                .pageNumber(customers.getNumber() + 1)
                .build();
    }
}


