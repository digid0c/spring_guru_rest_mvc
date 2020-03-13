package guru.samples.rest.mvc.api.v1.mapper;

import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerMapperUnitTest {

    private static final CustomerMapper CUSTOMER_MAPPER = CustomerMapper.INSTANCE;
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRST_NAME = "Sam";
    private static final String CUSTOMER_LAST_NAME = "Newman";

    @Test
    public void shouldMapCustomerToCustomerDTO() {
        Customer customer = Customer.builder()
                .id(CUSTOMER_ID)
                .firstName(CUSTOMER_FIRST_NAME)
                .lastName(CUSTOMER_LAST_NAME)
                .build();

        CustomerDTO customerDTO = CUSTOMER_MAPPER.customerToCustomerDTO(customer);

        assertThat(customerDTO, is(notNullValue()));
        assertThat(customerDTO.getId(), is(equalTo(CUSTOMER_ID)));
        assertThat(customerDTO.getFirstName(), is(equalTo(CUSTOMER_FIRST_NAME)));
        assertThat(customerDTO.getLastName(), is(equalTo(CUSTOMER_LAST_NAME)));
    }
}
