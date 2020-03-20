package guru.samples.rest.mvc.repository;

import guru.samples.rest.mvc.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
