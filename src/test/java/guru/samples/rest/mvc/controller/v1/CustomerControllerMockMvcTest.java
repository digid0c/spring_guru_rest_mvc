package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CustomerControllerMockMvcTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRST_NAME = "Sam";
    private static final String CUSTOMER_LAST_NAME = "Newman";

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController tested;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(tested).build();
    }

    @Test
    public void shouldFindAllCustomers() throws Exception {
        List<CustomerDTO> customers = asList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());
        when(customerService.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(customers.size())));
    }

    @Test
    public void shouldFindCustomerById() throws Exception {
        CustomerDTO customer = CustomerDTO.builder()
                .id(CUSTOMER_ID)
                .firstName(CUSTOMER_FIRST_NAME)
                .lastName(CUSTOMER_LAST_NAME)
                .build();
        when(customerService.findById(CUSTOMER_ID)).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(CUSTOMER_ID.intValue()))))
                .andExpect(jsonPath("$.firstName", is(equalTo(CUSTOMER_FIRST_NAME))))
                .andExpect(jsonPath("$.lastName", is(equalTo(CUSTOMER_LAST_NAME))));
    }
}
