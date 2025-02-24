//package vn.a18156.test.business.usecase;
//
//import org.springframework.http.HttpStatus;
//import vn.a18156.test.persistence.service.CustomerService;
//import vn.a18156.test.shared.mapper.CustomerMapper;
//import vn.a18156.test.shared.model.dto.CustomerDTO;
//import vn.a18156.test.shared.model.io.req.customer.CreateCustomerRQ;
//import vn.a18156.test.shared.model.io.req.customer.UpdateCustomerRQ;
//import vn.a18156.test.shared.model.io.res.ResObject;
//
//public class CRUD_UseCase  {
//    private final CustomerService customerService;
//    private final CustomerMapper customerMapper;
//    //create
//    public ResObject<CustomerDTO> execute(CreateCustomerRQ createCustomerRQ) {
//        var newCustomer = customerMapper.toEntityToCreate(createCustomerRQ);
//        var customer = customerService.create(newCustomer);
//        return ResObject.<CustomerDTO>builder()
//                .status("success")
//                .httpStatus(HttpStatus.CREATED)
//                .message("Thêm mới thành công")
//                .data(customerMapper.toDTO(customer))
//                .build();
//    }
//    //create
//    public ResObject<T> execute(I rq){
//        var create_new = mapper.toEntity(rq)); //mapper to entity
//        var update = service.create(create_new);
//        return ResObject.<T>builder()
//                .status("success")
//                .httpStatus(HttpStatus.CREATED)
//                .message("Thêm mới thành công")
//                .data(M) // to DTO
//                .build();
//    }
//    //delete
//    public ResObject<Void> execute(Long input) {
//        customerService.delete(input);
//        return ResObject.<Void>builder()
//                .status("success")
//                .httpStatus(HttpStatus.NO_CONTENT)
//                .message("Xoá thành công")
//                .data(null)
//                .build();
//    }
//
//    public ResObject<Void> execute(I){
//        S.delete(I);
//        return ResObject.<Void>builder()
//                .status("success")
//                .httpStatus(HttpStatus.NO_CONTENT)
//                .message("Xoá thành công")
//                .data(null)
//                .build();
//    }
//
//    //update
//    public ResObject<CustomerDTO> execute(UpdateCustomerRQ input) {
//        var update = customerService.update(customerMapper.toEntityToUpdate(input));
//        return ResObject.<CustomerDTO>builder()
//                .status("success")
//                .httpStatus(HttpStatus.OK)
//                .message("Cập nhật thành công")
//                .data(customerMapper.toDTO(update))
//                .build();
//    }
//
//}
