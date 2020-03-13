package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.api.v1.model.CustomerListDTO;
import guru.samples.rest.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseStatus(OK)
    public CustomerListDTO findAll() {
        return new CustomerListDTO(customerService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerDTO findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        return customerService.create(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.update(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerDTO patch(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patch(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
