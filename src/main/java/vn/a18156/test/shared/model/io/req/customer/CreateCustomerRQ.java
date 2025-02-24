package vn.a18156.test.shared.model.io.req.customer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateCustomerRQ {
    private String name;
    private Boolean gender;
    private String address;
    private String phone;
}
