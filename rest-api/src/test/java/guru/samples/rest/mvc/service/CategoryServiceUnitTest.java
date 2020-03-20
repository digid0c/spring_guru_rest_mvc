package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.mapper.CategoryMapper;
import guru.samples.rest.mvc.api.v1.model.CategoryDTO;
import guru.samples.rest.mvc.domain.Category;
import guru.samples.rest.mvc.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CategoryServiceUnitTest {

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "Some name";

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService tested;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        tested = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void shouldFindAllCategories() {
        List<Category> categories = asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = tested.findAll();

        assertThat(categoryDTOs, is(notNullValue()));
        assertThat(categoryDTOs.size(), is(equalTo(categories.size())));
    }

    @Test
    public void shouldFindCategoryByName() {
        Category category = Category.builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .build();
        when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(category);

        CategoryDTO categoryDTO = tested.findByName(CATEGORY_NAME);

        assertThat(categoryDTO, is(notNullValue()));
        assertThat(categoryDTO.getId(), is(equalTo(CATEGORY_ID)));
        assertThat(categoryDTO.getName(), is(equalTo(CATEGORY_NAME)));
    }
}
