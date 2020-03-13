package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.mapper.CustomerMapper;
import guru.samples.rest.mvc.api.v1.model.CustomerDTO;
import guru.samples.rest.mvc.exception.ResourceNotFoundException;
import guru.samples.rest.mvc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(toList());
    }

    @Override
    public CustomerDTO findById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        return createOrUpdate(customerDTO);
    }

    @Override
    public CustomerDTO update(Long customerId, CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return createOrUpdate(customerDTO);
    }

    @Override
    public CustomerDTO patch(Long customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    ofNullable(customerDTO.getFirstName()).ifPresent(customer::setFirstName);
                    ofNullable(customerDTO.getLastName()).ifPresent(customer::setLastName);
                    return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    private CustomerDTO createOrUpdate(CustomerDTO customerDTO) {
        return Optional.of(customerDTO)
                .map(customerMapper::customerDTOToCustomer)
                .map(customerRepository::save)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
