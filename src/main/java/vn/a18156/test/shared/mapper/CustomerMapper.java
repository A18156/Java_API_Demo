package vn.a18156.test.shared.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import vn.a18156.test.shared.model.dto.CustomerDTO;
import vn.a18156.test.shared.model.entity.Customer;
import vn.a18156.test.shared.model.io.req.customer.CreateCustomerRQ;
import vn.a18156.test.shared.model.io.req.customer.UpdateCustomerRQ;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toEntityToCreate(CreateCustomerRQ input);
    Customer toEntityToUpdate(UpdateCustomerRQ input);
    @Mapping(source = "createAt", target = "create_at")
    CustomerDTO toDTO(Customer customer);
}
