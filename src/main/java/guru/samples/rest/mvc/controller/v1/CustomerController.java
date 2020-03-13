package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.api.v1.model.CustomerListDTO;
import guru.samples.rest.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static guru.samples.rest.mvc.controller.v1.CustomerController.BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> findAll() {
        return new ResponseEntity<>(new CustomerListDTO(customerService.findAll()), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findById(id), OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.create(customerDTO), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.update(id, customerDTO), OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patch(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.patch(id, customerDTO), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(OK);
    }
}
