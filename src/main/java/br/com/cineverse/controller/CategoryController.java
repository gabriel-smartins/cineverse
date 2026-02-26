package br.com.cineverse.controller;

import br.com.cineverse.controller.dto.request.CategoryRequestDTO;
import br.com.cineverse.controller.dto.response.CategoryResponseDTO;
import br.com.cineverse.entity.Category;
import br.com.cineverse.mapper.CategoryMapper;
import br.com.cineverse.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cineverse/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryService.findAll();

        return categories.stream()
                .map(CategoryMapper::toCategoryResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable("id") Long categoryId) {
        var category = categoryService.findById(categoryId);

        if (category.isPresent()) {
            return CategoryMapper.toCategoryResponseDTO(category.get());
        }
        return null;
    }

    @PostMapping
    public CategoryResponseDTO saveCategory(@RequestBody CategoryRequestDTO request) {
        var category = CategoryMapper.toCategory(request);
        var savedCategory = categoryService.saveCategory(category);
        return CategoryMapper.toCategoryResponseDTO(savedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
