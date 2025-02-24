package vn.a18156.test.business.usecase.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vn.a18156.test.business.usecase.UseCase;
import vn.a18156.test.persistence.service.CustomerService;
import vn.a18156.test.shared.mapper.CustomerMapper;
import vn.a18156.test.shared.model.dto.CustomerDTO;
import vn.a18156.test.shared.model.io.res.ResPage;


@Component
@RequiredArgsConstructor
public class GetCustomerUseCase implements UseCase<Pageable, ResPage<CustomerDTO>> {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    //    @Override
//    public Page<CustomerDTO> execute(Pageable pageable) {
//        return customerService.getCustomers(pageable)
//                .map(customerMapper::toDTO);
//    }
    /*
    sử dụng tính năng tự động của pageable để phân trang
     */
    @Override
    public ResPage<CustomerDTO> execute(Pageable pageable) {
        Page<CustomerDTO> page = customerService.getCustomers(pageable)
                .map(customerMapper::toDTO);
        if (pageable.getPageNumber() > (page.getTotalPages() - 1)) {
            throw new IllegalArgumentException("Số trang tối đa " + page.getTotalPages());
        }
        return ResPage.<CustomerDTO>builder()
                .data(page.getContent())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .size(page.getSize())
                .pageNumber(page.getNumber() + 1)
                .build();
    }
}
