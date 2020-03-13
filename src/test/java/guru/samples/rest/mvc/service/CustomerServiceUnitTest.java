package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.mapper.CustomerMapper;
import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.domain.Customer;
import guru.samples.rest.mvc.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomerServiceUnitTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRST_NAME = "Sam";
    private static final String CUSTOMER_LAST_NAME = "Newman";

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService tested;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        tested = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void shouldFindAllCustomers() {
        List<Customer> customers = asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOs = tested.findAll();

        assertThat(customerDTOs, is(notNullValue()));
        assertThat(customerDTOs.size(), is(equalTo(customers.size())));
    }

    @Test
    public void shouldFindCustomerById() {
        Customer customer = Customer.builder()
                .id(CUSTOMER_ID)
                .firstName(CUSTOMER_FIRST_NAME)
                .lastName(CUSTOMER_LAST_NAME)
                .build();
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = tested.findById(CUSTOMER_ID);

        assertThat(customerDTO, is(notNullValue()));
        assertThat(customerDTO.getId(), is(equalTo(CUSTOMER_ID)));
        assertThat(customerDTO.getFirstName(), is(equalTo(CUSTOMER_FIRST_NAME)));
        assertThat(customerDTO.getLastName(), is(equalTo(CUSTOMER_LAST_NAME)));
    }
}
