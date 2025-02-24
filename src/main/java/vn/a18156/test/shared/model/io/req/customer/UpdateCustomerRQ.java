package vn.a18156.test.shared.model.io.req.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCustomerRQ {
    private int id;
    @NotBlank
    private String name;
    private Boolean gender;
    private String address;
    @NotBlank
    private String phone;
}
