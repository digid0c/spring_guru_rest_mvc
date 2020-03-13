package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.CategoryDTO;
import guru.samples.rest.mvc.exception.ResourceNotFoundException;
import guru.samples.rest.mvc.exception.handler.RestResponseEntityExceptionHandler;
import guru.samples.rest.mvc.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static guru.samples.rest.mvc.controller.v1.CategoryController.BASE_URL;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CategoryControllerMockMvcTest {

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "test";

    private static final String BASE_URL_WITH_CATEGORY_NAME = BASE_URL + "/" + CATEGORY_NAME;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController tested;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(tested)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void shouldFindAllCategories() throws Exception {
        List<CategoryDTO> categories = asList(new CategoryDTO(), new CategoryDTO(), new CategoryDTO());
        when(categoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get(BASE_URL)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(categories.size())));
    }

    @Test
    public void shouldFindCategoryByName() throws Exception {
        CategoryDTO category = CategoryDTO.builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .build();
        when(categoryService.findByName(CATEGORY_NAME)).thenReturn(category);

        mockMvc.perform(get(BASE_URL_WITH_CATEGORY_NAME)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(CATEGORY_ID.intValue()))))
                .andExpect(jsonPath("$.name", is(equalTo(CATEGORY_NAME))));
    }

    @Test
    public void shouldThrowResourceNotFoundException() throws Exception {
        when(categoryService.findByName(CATEGORY_NAME)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(BASE_URL_WITH_CATEGORY_NAME)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
