package vn.a18156.test.business.usecase.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vn.a18156.test.business.usecase.UseCase;
import vn.a18156.test.persistence.service.CustomerService;
import vn.a18156.test.shared.mapper.CustomerMapper;
import vn.a18156.test.shared.model.dto.CustomerDTO;
import vn.a18156.test.shared.model.io.req.customer.UpdateCustomerRQ;
import vn.a18156.test.shared.model.io.res.ResObject;

@Component
@RequiredArgsConstructor
public class UpdateCustomerUseCase implements UseCase<UpdateCustomerRQ, ResObject<CustomerDTO>> {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Override
    public ResObject<CustomerDTO> execute(UpdateCustomerRQ input) {
        var update = customerService.update(customerMapper.toEntityToUpdate(input));
        return ResObject.<CustomerDTO>builder()
                .status("success")
                .httpStatus(HttpStatus.OK)
                .message("Cập nhật thành công")
                .data(customerMapper.toDTO(update))
                .build();
    }
}
