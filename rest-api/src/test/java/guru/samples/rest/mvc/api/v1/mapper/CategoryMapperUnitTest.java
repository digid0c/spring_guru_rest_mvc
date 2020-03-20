package guru.samples.rest.mvc.api.v1.mapper;

import guru.samples.rest.mvc.api.v1.model.CategoryDTO;
import guru.samples.rest.mvc.domain.Category;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CategoryMapperUnitTest {

    private static final CategoryMapper CATEGORY_MAPPER = CategoryMapper.INSTANCE;
    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "Some name";

    @Test
    public void shouldMapCategoryToCategoryDTO() {
        Category category = Category.builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .build();

        CategoryDTO categoryDTO = CATEGORY_MAPPER.categoryToCategoryDTO(category);

        assertThat(categoryDTO, is(notNullValue()));
        assertThat(categoryDTO.getId(), is(equalTo(CATEGORY_ID)));
        assertThat(categoryDTO.getName(), is(equalTo(CATEGORY_NAME)));
    }
}
