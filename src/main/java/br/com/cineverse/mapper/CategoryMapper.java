package br.com.cineverse.mapper;

import br.com.cineverse.controller.dto.request.CategoryRequestDTO;
import br.com.cineverse.controller.dto.response.CategoryResponseDTO;
import br.com.cineverse.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequestDTO categoryRequestDTO){
        return Category
                .builder()
                .name(categoryRequestDTO.name())
                .build();
    }

    public static CategoryResponseDTO toCategoryResponseDTO(Category category){
        return CategoryResponseDTO
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
