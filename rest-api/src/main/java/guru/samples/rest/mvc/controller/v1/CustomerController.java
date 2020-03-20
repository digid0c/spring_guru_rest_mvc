package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.service.CustomerService;
import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.api.v1.model.CustomerListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static guru.samples.rest.mvc.controller.v1.CustomerController.BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Api(description = "This API allows to perform CRUD operations with customers")
@RestController
@RequestMapping(BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Get a list of all customers")
    @GetMapping
    @ResponseStatus(OK)
    public CustomerListDTO findAll() {
        return new CustomerListDTO(customerService.findAll());
    }

    @ApiOperation(value = "Find a customer with specified ID", notes = "Throws ResourceNotFoundException")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerDTO findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @ApiOperation(value = "Create a new customer")
    @PostMapping
    @ResponseStatus(CREATED)
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        return customerService.create(customerDTO);
    }

    @ApiOperation(value = "Full update of an existing customer details", notes = "Throws ResourceNotFoundException")
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.update(id, customerDTO);
    }

    @ApiOperation(value = "Patch an existing customer specified properties", notes = "Throws ResourceNotFoundException")
    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerDTO patch(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patch(id, customerDTO);
    }

    @ApiOperation(value = "Delete a customer")
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
