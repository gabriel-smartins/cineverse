package br.com.cineverse.controller;

import br.com.cineverse.controller.dto.request.CategoryRequestDTO;
import br.com.cineverse.controller.dto.response.CategoryResponseDTO;
import br.com.cineverse.entity.Category;
import br.com.cineverse.mapper.CategoryMapper;
import br.com.cineverse.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cineverse/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAll() {
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok((categories.stream()
                .map(CategoryMapper::toCategoryResponseDTO)
                .toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable("id") Long categoryId) {
        return categoryService.findById(categoryId)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponseDTO(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@RequestBody CategoryRequestDTO request) {
        var category = CategoryMapper.toCategory(request);
        var savedCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponseDTO(savedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
