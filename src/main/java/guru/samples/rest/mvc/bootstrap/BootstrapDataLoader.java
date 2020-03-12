package guru.samples.rest.mvc.bootstrap;

import guru.samples.rest.mvc.domain.Category;
import guru.samples.rest.mvc.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class BootstrapDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public BootstrapDataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            loadCategories();
        }
    }

    private void loadCategories() {
        log.debug("Loading categories...");

        List<Category> categories = Stream.of("Fruits", "Dried", "Fresh", "Exotic", "Nuts")
                .map(categoryName -> Category.builder().name(categoryName).build())
                .collect(toList());

        categoryRepository.saveAll(categories);

        log.debug("Categories loaded!");
    }
}
