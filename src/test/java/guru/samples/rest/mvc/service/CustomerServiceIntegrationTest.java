package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.mapper.CustomerMapper;
import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.bootstrap.BootstrapDataLoader;
import guru.samples.rest.mvc.domain.Customer;
import guru.samples.rest.mvc.repository.CategoryRepository;
import guru.samples.rest.mvc.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Optional.ofNullable;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private CustomerService tested;

    @BeforeEach
    public void setUp() {
        BootstrapDataLoader bootstrapDataLoader = new BootstrapDataLoader(categoryRepository, customerRepository);
        bootstrapDataLoader.run();

        tested = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void shouldPatchCustomerFirstName() {
        String newFirstName = "Jurgen";
        Long customerId = getCustomerId();

        Customer originalCustomer = customerRepository.findById(customerId)
                .orElseThrow(RuntimeException::new);
        CustomerDTO customerToPatch = CustomerDTO.builder()
                .firstName(newFirstName)
                .build();

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        tested.patch(customerId, customerToPatch);
        Customer patchedCustomer = customerRepository.findById(customerId).orElse(null);

        assertThat(patchedCustomer, is(notNullValue()));
        assertThat(patchedCustomer.getFirstName(), is(equalTo(newFirstName)));
        assertThat(patchedCustomer.getFirstName(), is(not(equalTo(originalFirstName))));
        assertThat(patchedCustomer.getLastName(), is(equalTo(originalLastName)));
    }

    @Test
    public void shouldPatchCustomerLastName() {
        String newLastName = "Klopp";
        Long customerId = getCustomerId();

        Customer originalCustomer = customerRepository.findById(customerId)
                .orElseThrow(RuntimeException::new);
        CustomerDTO customerToPatch = CustomerDTO.builder()
                .lastName(newLastName)
                .build();

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        tested.patch(customerId, customerToPatch);
        Customer patchedCustomer = customerRepository.findById(customerId).orElse(null);

        assertThat(patchedCustomer, is(notNullValue()));
        assertThat(patchedCustomer.getLastName(), is(equalTo(newLastName)));
        assertThat(patchedCustomer.getLastName(), is(not(equalTo(originalLastName))));
        assertThat(patchedCustomer.getFirstName(), is(equalTo(originalFirstName)));
    }

    private Long getCustomerId() {
        return ofNullable(customerRepository.findAll().get(0))
                .map(Customer::getId)
                .orElse(null);
    }
}
