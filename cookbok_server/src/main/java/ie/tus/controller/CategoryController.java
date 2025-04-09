/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2025
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package ie.tus.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.tus.entities.Category;
import ie.tus.services.CategoryService;
import ie.tus.services.CookbookService;
import jakarta.inject.Inject;

@RestController
public class CategoryController {
    public static final String TRACE_ID = "cookbookap-trace-id";

    private static final Logger log = LoggerFactory.getLogger(CookBookController.class);

    CategoryService categoryService;

    @Inject
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<HashMap> getAllCategories(@RequestHeader(TRACE_ID) String correlationId){
        log.error("getAllCategories::Correlation id: {}",correlationId);
        try {
            final HashMap<Integer, String> allCategories = categoryService.getAllCategories();
            log.error("getAllCategories::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(allCategories);
        } catch (Exception e){
            log.error("getAllCategories::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.error("getCategory::Correlation id: {}",correlationId);
        try {
            final Category categoryById = categoryService.getCategoryById(id);
            log.error("getCategory::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(categoryById);
        } catch (Exception e){
            log.error("getCategory::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestParam("categoryName") String name, @RequestHeader(TRACE_ID) String correlationId){
        log.error("createCategory::Correlation id: {}",correlationId);
        try {
            final Category categoryById = categoryService.createCategory(name);
            log.error("createCategory::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(categoryById);
        } catch (Exception e){
            log.error("createCategory::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategoryName(@PathVariable int id, @RequestHeader("newCategoryName") String name, @RequestHeader(TRACE_ID) String correlationId){
        log.error("updateCategoryName::Correlation id: {}",correlationId);
        try {
            final Category categoryById = categoryService.updateCategory(id, name);
            log.error("updateCategoryName::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(categoryById);
        } catch (Exception e){
            log.error("updateCategoryName::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.error("deleteCategory::Correlation id: {}",correlationId);
        try {
            categoryService.deleteCategory(id);
            log.error("deleteCategory::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok("Category "+id+" deleted");
        } catch (Exception e){
            log.error("deleteCategory::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
