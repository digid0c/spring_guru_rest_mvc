package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO findByName(String name);
}
