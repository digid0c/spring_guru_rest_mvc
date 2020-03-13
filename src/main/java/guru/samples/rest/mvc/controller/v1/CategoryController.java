package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.CategoryDTO;
import guru.samples.rest.mvc.api.v1.model.CategoryListDTO;
import guru.samples.rest.mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> findAll() {
        return new ResponseEntity<>(new CategoryListDTO(categoryService.findAll()), OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> findByName(@PathVariable String name) {
        return new ResponseEntity<>(categoryService.findByName(name), OK);
    }
}
