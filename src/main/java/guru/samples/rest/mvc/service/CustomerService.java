package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(Long id);

    CustomerDTO create(CustomerDTO customerDTO);
}
