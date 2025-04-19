package ie.tus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import ie.tus.DTO.AddRecipesToBookDTO;
import ie.tus.DTO.RecipeDTO;
import ie.tus.entities.Recipe;
import ie.tus.entities.RecipesByBook;
import ie.tus.services.RecipesService;
import jakarta.validation.Valid;

@RestController
public class RecipesController {

    public static final String TRACE_ID = "cookbookap-trace-id";

    private static final Logger log = LoggerFactory.getLogger(RecipesController.class);
    @Autowired
    RecipesService recipesService;

    @GetMapping("/recipes/byBook/{bookId}")
    public ResponseEntity<RecipesByBook> getCookbookRecipes(@PathVariable int bookId){
        String correlationId = "asdasdad";
        log.error("getCookbookRecipes::Correlation id: {}",correlationId);
        try {
            final RecipesByBook recipesBook = recipesService.getRecipesBook(bookId);
            log.error("getCookbookRecipes::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(recipesBook);
        } catch (Exception e){
            log.error("getCookbookRecipes::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.error("getRecipe::Correlation id: {}",correlationId);
        try {
            final Recipe recipe = recipesService.getRecipe(id);
            log.error("getRecipe::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(recipe);
        }catch (Exception e){
            log.error("getRecipe::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/recipes/all")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestHeader(TRACE_ID) String correlationId){
        log.error("getRecipes::Correlation id: {}",correlationId);
        try {
            final List<Recipe> allRecipes = recipesService.getAllRecipe();
            log.error("getRecipes::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(allRecipes);
        } catch (Exception e){
            log.error("getRecipes::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.error("deleteRecipe::Correlation id: {}",correlationId);
        try {
            final Recipe recipe = recipesService.getRecipe(id);
            recipesService.deleteRecipe(id);
            log.error("deleteRecipe::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok("Recipe: "+recipe.getTitle()+": id "+recipe.getRecipeId()+" deleted");
        } catch (Exception e){
            log.error("deleteRecipe::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/recipes/byBook/{bookId}")
    public ResponseEntity deleteCookBook(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.error("deleteCookBook::Correlation id: {}",correlationId);
        try {
            recipesService.deleteRecipesBook(id);
            log.error("deleteCookBook::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok("deleted");
        } catch (Exception e){
            log.error("deleteCookBook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/recipes")
    public ResponseEntity createRecipe(@Valid @RequestBody RecipeDTO recipe, @RequestHeader(TRACE_ID) String correlationId){
        log.error("createRecipe::Correlation id: {}",correlationId);
        try {
            final Recipe savedRecipe = recipesService.saveRecipe(recipe);
            log.error("createRecipe::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(savedRecipe);
        } catch (Exception e){
            log.error("createRecipe::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping("/recipes/byBook")
    public ResponseEntity saveRecipesToBook(@Valid @RequestBody AddRecipesToBookDTO addRecipesToBookDTO, @RequestHeader(TRACE_ID) String correlationId){
        log.error("saveRecipesToBook::Correlation id: {}",correlationId);
        try {
            final RecipesByBook recipesByBook = recipesService.saveRecipesToBook(addRecipesToBookDTO);
            log.error("saveRecipesToBook::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(recipesByBook);
        } catch (Exception e){
            log.error("saveRecipesToBook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity updateRecipe(@Valid @PathVariable int id, @RequestBody RecipeDTO dto, @RequestHeader(TRACE_ID) String correlationId){
        log.error("updateRecipe::Correlation id: {}",correlationId);
        try {
            final Recipe recipe = recipesService.updateRecipe(id, dto);
            log.error("updateRecipe::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(recipe);
        } catch (Exception e){
            log.error("updateRecipe::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/recipes/byBook/{id}")
    public ResponseEntity updateCookbook(@PathVariable int id, @RequestBody AddRecipesToBookDTO dto, @RequestHeader(TRACE_ID) String correlationId){
        log.error("updateCookbook::Correlation id: {}",correlationId);
        try {
            final RecipesByBook recipesByBook = recipesService.addRecipeToBook(id, dto.getRecipesIds());
            log.error("updateCookbook::Executed::Correlation id: {}", correlationId);

            return ResponseEntity.ok(recipesByBook);
        } catch (Exception e) {
            log.error("updateCookbook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
