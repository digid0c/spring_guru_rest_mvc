package guru.samples.rest.mvc.api.v1.mapper;

import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
