package vn.a18156.test.business.usecase.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vn.a18156.test.business.usecase.UseCase;
import vn.a18156.test.persistence.service.CustomerService;
import vn.a18156.test.shared.mapper.CustomerMapper;
import vn.a18156.test.shared.model.dto.CustomerDTO;
import vn.a18156.test.shared.model.io.req.customer.CreateCustomerRQ;
import vn.a18156.test.shared.model.io.res.ResObject;

@Component
@RequiredArgsConstructor
public class CreateCustomerUseCase implements UseCase<CreateCustomerRQ, ResObject<CustomerDTO>> {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

//cách 1: trả về dạng cơ bảng
//    @Override
//    public CustomerDTO execute(CreateCustomerRQ input) {
//        var newCustomer = customerMapper.toEntityToCreate(input);
//        var customer = customerService.create(newCustomer);
//        return customerMapper.toDTO(customer);
//    }
//cách 2:
//trả về với custom response
    //dùng 1 object với responseObject
    @Override
    public ResObject<CustomerDTO> execute(CreateCustomerRQ createCustomerRQ) {
        var newCustomer = customerMapper.toEntityToCreate(createCustomerRQ);
        var customer = customerService.create(newCustomer);
        return ResObject.<CustomerDTO>builder()
                .status("success")
                .httpStatus(HttpStatus.CREATED)
                .message("Thêm mới thành công")
                .data(customerMapper.toDTO(customer))
                .build();
    }
}
