package vn.a18156.test.business.usecase.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vn.a18156.test.business.usecase.UseCase;
import vn.a18156.test.persistence.service.CustomerService;
import vn.a18156.test.shared.model.io.res.ResObject;

@Component
@RequiredArgsConstructor
public class DeleteCustomerUseCase implements UseCase<Long, ResObject<Void>> {
    private final CustomerService customerService;

    @Override
    public ResObject<Void> execute(Long input) {
        customerService.delete(input);
        return ResObject.<Void>builder()
                .status("success")
                .httpStatus(HttpStatus.NO_CONTENT)
                .message("Xoá thành công")
                .data(null)
                .build();
    }
}
