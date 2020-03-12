package guru.samples.rest.mvc.api.v1.mapper;

import guru.samples.rest.mvc.api.v1.model.CategoryDTO;
import guru.samples.rest.mvc.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
