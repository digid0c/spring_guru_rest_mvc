package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.exception.ResourceNotFoundException;
import guru.samples.rest.mvc.exception.handler.RestResponseEntityExceptionHandler;
import guru.samples.rest.mvc.model.CustomerDTO;
import guru.samples.rest.mvc.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static guru.samples.rest.mvc.controller.v1.CustomerController.BASE_URL;
import static guru.samples.rest.mvc.controller.v1.RestControllerMockMvcTestHelper.asJsonString;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CustomerControllerMockMvcTest {

    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRST_NAME = "Sam";
    private static final String CUSTOMER_LAST_NAME = "Newman";

    private static final String BASE_URL_WITH_CUSTOMER_ID = BASE_URL + "/" + CUSTOMER_ID;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController tested;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(tested)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void shouldFindAllCustomers() throws Exception {
        List<CustomerDTO> customers = asList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());
        when(customerService.findAll()).thenReturn(customers);

        mockMvc.perform(get(BASE_URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(customers.size())));
    }

    @Test
    public void shouldFindCustomerById() throws Exception {
        CustomerDTO customer = createExistingCustomer();
        when(customerService.findById(CUSTOMER_ID)).thenReturn(customer);

        mockMvc.perform(get(BASE_URL_WITH_CUSTOMER_ID)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(CUSTOMER_ID.intValue()))))
                .andExpect(jsonPath("$.firstName", is(equalTo(CUSTOMER_FIRST_NAME))))
                .andExpect(jsonPath("$.lastName", is(equalTo(CUSTOMER_LAST_NAME))));
    }

    @Test
    public void shouldCreateNewCustomer() throws Exception {
        CustomerDTO customerToCreate = createIncomingRequestCustomerData();
        CustomerDTO existingCustomer = createExistingCustomer();
        when(customerService.create(any(CustomerDTO.class))).thenReturn(existingCustomer);

        mockMvc.perform(post(BASE_URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(asJsonString(customerToCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(equalTo(CUSTOMER_ID.intValue()))))
                .andExpect(jsonPath("$.firstName", is(equalTo(CUSTOMER_FIRST_NAME))))
                .andExpect(jsonPath("$.lastName", is(equalTo(CUSTOMER_LAST_NAME))));
    }

    @Test
    public void shouldUpdateExistingCustomer() throws Exception {
        CustomerDTO customerToUpdate = createIncomingRequestCustomerData();
        CustomerDTO existingCustomer = createExistingCustomer();
        when(customerService.update(eq(CUSTOMER_ID), any(CustomerDTO.class))).thenReturn(existingCustomer);

        mockMvc.perform(put(BASE_URL_WITH_CUSTOMER_ID)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(asJsonString(customerToUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(CUSTOMER_ID.intValue()))))
                .andExpect(jsonPath("$.firstName", is(equalTo(CUSTOMER_FIRST_NAME))))
                .andExpect(jsonPath("$.lastName", is(equalTo(CUSTOMER_LAST_NAME))));
    }

    @Test
    public void shouldPatchExistingCustomer() throws Exception {
        CustomerDTO customerToPatch = createIncomingRequestCustomerData();
        CustomerDTO existingCustomer = createExistingCustomer();
        when(customerService.patch(eq(CUSTOMER_ID), any(CustomerDTO.class))).thenReturn(existingCustomer);

        mockMvc.perform(patch(BASE_URL_WITH_CUSTOMER_ID)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(asJsonString(customerToPatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(CUSTOMER_ID.intValue()))))
                .andExpect(jsonPath("$.firstName", is(equalTo(CUSTOMER_FIRST_NAME))))
                .andExpect(jsonPath("$.lastName", is(equalTo(CUSTOMER_LAST_NAME))));
    }

    @Test
    public void shouldDeleteExistingCustomer() throws Exception {
        mockMvc.perform(delete(BASE_URL_WITH_CUSTOMER_ID)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).delete(CUSTOMER_ID);
    }

    @Test
    public void shouldThrowResourceNotFoundException() throws Exception {
        when(customerService.findById(CUSTOMER_ID)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(BASE_URL_WITH_CUSTOMER_ID)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private CustomerDTO createExistingCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(CUSTOMER_ID);
        customerDTO.setFirstName(CUSTOMER_FIRST_NAME);
        customerDTO.setLastName(CUSTOMER_LAST_NAME);

        return customerDTO;
    }

    private CustomerDTO createIncomingRequestCustomerData() {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName(CUSTOMER_FIRST_NAME);
        customerDTO.setLastName(CUSTOMER_LAST_NAME);

        return customerDTO;
    }
}
