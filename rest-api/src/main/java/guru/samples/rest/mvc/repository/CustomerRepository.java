package guru.samples.rest.mvc.repository;

import guru.samples.rest.mvc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
