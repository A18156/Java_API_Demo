package vn.a18156.test.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.a18156.test.business.usecase.customer.*;
import vn.a18156.test.shared.model.dto.CustomerDTO;
import vn.a18156.test.shared.model.io.req.customer.CreateCustomerRQ;
import vn.a18156.test.shared.model.io.req.customer.UpdateCustomerRQ;
import vn.a18156.test.shared.model.io.res.ResObject;
import vn.a18156.test.shared.model.io.res.ResPage;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final SearchCustomerUseCase searchCustomerUseCase;

//    @PostMapping("/create")
//    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CreateCustomerRQ request) {
//        var customer = createCustomerUseCase.execute(request);
//        return new ResponseEntity<>(customer, HttpStatus.CREATED);
//    }
    @PostMapping("/create")
    public ResponseEntity<ResObject<CustomerDTO>> create(@Valid @RequestBody CreateCustomerRQ request) {
        var customer = createCustomerUseCase.execute(request);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ResPage<CustomerDTO>> getCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.by(Sort.Direction.DESC, "id"));
        ResPage<CustomerDTO> customers = getCustomerUseCase.execute(pageable);
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/update")
    public ResponseEntity<ResObject<CustomerDTO>> update(@Valid @RequestBody UpdateCustomerRQ request) {
        var customer = updateCustomerUseCase.execute(request);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResObject<Void>> delete(@PathVariable Long id) {
        var res = deleteCustomerUseCase.execute(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResPage<CustomerDTO>> search(
            @Valid
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of((page - 1), size, Sort.by(Sort.Direction.DESC, "id"));
        var result = searchCustomerUseCase.execute(keyword, pageable);
        return ResponseEntity.ok(result);
    }
}
