package guru.samples.rest.mvc.bootstrap;

import guru.samples.rest.mvc.domain.Category;
import guru.samples.rest.mvc.domain.Customer;
import guru.samples.rest.mvc.domain.Vendor;
import guru.samples.rest.mvc.repository.CategoryRepository;
import guru.samples.rest.mvc.repository.CustomerRepository;
import guru.samples.rest.mvc.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class BootstrapDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public BootstrapDataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        if (categoryRepository.count() != 0) {
            return;
        }
        log.debug("Loading categories...");

        List<Category> categories = Stream.of("Fruits", "Dried", "Fresh", "Exotic", "Nuts")
                .map(categoryName -> Category.builder().name(categoryName).build())
                .collect(toList());

        categoryRepository.saveAll(categories);

        log.debug("Categories loaded!");
    }

    private void loadCustomers() {
        if (customerRepository.count() != 0) {
            return;
        }
        log.debug("Loading customers...");

        List<Customer> customers = Stream.of(Pair.of("Michael", "Weston"), Pair.of("Sam", "Axe"))
                .map(namePair -> Customer.builder()
                        .firstName(namePair.getFirst())
                        .lastName(namePair.getSecond())
                        .build())
                .collect(toList());

        customerRepository.saveAll(customers);

        log.debug("Customers loaded!");
    }

    private void loadVendors() {
        if (vendorRepository.count() != 0) {
            return;
        }
        log.debug("Loading vendors...");

        List<Vendor> vendors = Stream.of("McDonalds", "KFC", "Pizza Hut")
                .map(vendorName -> Vendor.builder().name(vendorName).build())
                .collect(toList());

        vendorRepository.saveAll(vendors);

        log.debug("Vendors loaded!");
    }
}
