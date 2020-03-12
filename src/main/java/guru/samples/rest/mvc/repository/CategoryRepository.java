package guru.samples.rest.mvc.repository;

import guru.samples.rest.mvc.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}