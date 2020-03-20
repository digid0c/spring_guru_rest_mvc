package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.CategoryDTO;
import guru.samples.rest.mvc.api.v1.model.CategoryListDTO;
import guru.samples.rest.mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static guru.samples.rest.mvc.controller.v1.CategoryController.BASE_URL;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(OK)
    public CategoryListDTO findAll() {
        return new CategoryListDTO(categoryService.findAll());
    }

    @GetMapping("/{name}")
    @ResponseStatus(OK)
    public CategoryDTO findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }
}
