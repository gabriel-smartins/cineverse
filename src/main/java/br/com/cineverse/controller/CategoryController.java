package br.com.cineverse.controller;

import br.com.cineverse.entity.Category;
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
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long categoryId) {
        var category = categoryService.findById(categoryId);

        if (category.isPresent()) {
            return category.get();
        }

        return null;
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
