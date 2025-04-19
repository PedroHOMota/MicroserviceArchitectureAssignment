package ie.tus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.CookbookDTO;
import ie.tus.DTO.RecipesByBookDTO;
import ie.tus.entities.Category;
import ie.tus.entities.Cookbook;
import ie.tus.services.CookbookService;
import jakarta.inject.Inject;

@RestController
public class CookBookController {

    public static final String TRACE_ID = "cookbookap-trace-id";

    private static final Logger log = LoggerFactory.getLogger(CookBookController.class);

    CookbookService cookbookService;

    @Inject
    public CookBookController(CookbookService cookbookService){
        this.cookbookService = cookbookService;
    }

    @GetMapping("/cookbook")
    public ResponseEntity<List<Cookbook>> getAllCookbooks(@RequestHeader(TRACE_ID) String correlationId){
        log.error("getAllCookbooks::Correlation id: {}",correlationId);
        try {
            final List<Cookbook> allCookbooksL = cookbookService.getAllCookbooksL();
            log.error("getAllCookbooks::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(allCookbooksL);
        } catch (Exception e){
            log.error("getCookbook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cookbook/break")
    public ResponseEntity<String> timeOutRequest(@RequestHeader(TRACE_ID) String correlationId) throws Exception{
        Thread.sleep(15000);
        log.error("getAllCookbooks::Correlation id: {}",correlationId);
        return ResponseEntity.ok("allCookbooks");

    }

    @GetMapping("/cookbook/{id}")
    public ResponseEntity<Cookbook> getCookbook(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("getCookbook::Correlation id: {}",correlationId);
        try {
            Cookbook cookBookById = cookbookService.getCookBookById(id);
            log.error("getCookbook::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(cookBookById);
        } catch (Exception e){
            log.error("getCookbook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cookbook/{id}/recipes")
    public ResponseEntity<RecipesByBookDTO> getCookbookRecipes(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("getCookbookRecipes::Correlation id: {}",correlationId);
        try {
            final RecipesByBookDTO recipesForBook = cookbookService.getRecipesForBook(id);
            log.error("getCookbookRecipes::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(recipesForBook);
        } catch (Exception e){
            log.error("getCookbookRecipes::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/cookbook/{id}")
    public ResponseEntity<String> deleteCookBook(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("deleteCookBook::Correlation id: {}",correlationId);
        try {
            cookbookService.deleteCookBook(id);
            log.error("deleteCookBook::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok("deleted");
        } catch (Exception e){
            log.error("deleteCookBook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/cookbook/{id}/recipes")
    public ResponseEntity<String> deleteCookBookRecipes(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("deleteCookBookRecipes::Correlation id: {}",correlationId);
        log.error("deleteCookBookRecipes::Executed::Correlation id: {}",correlationId);
        //send to second one

        //cookbookService.getRecipes(id);
        cookbookService.deleteCookBook(id);
        return ResponseEntity.ok("0recipes");
    }

    @PostMapping("/cookbook")
    public ResponseEntity<Cookbook> createCookbook(@RequestBody final CookbookDTO cookbookDTO, @RequestHeader(TRACE_ID) String correlationId){
        log.error("createCookbook::Correlation id: {}",correlationId);
        System.out.println("createCookbook::Correlation id: "+correlationId);
        try {
            final Cookbook cookBook = cookbookService.createCookBook(cookbookDTO);
            log.error("createCookbook::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(cookBook);
        } catch (Exception e){
            log.error("createCookbook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/cookbook/{id}")
    public ResponseEntity<RecipesByBookDTO> saveRecipesToCookbook(@PathVariable final int id, @RequestBody final AddRecipesToBook recipes,@RequestHeader(TRACE_ID) String correlationId){
        log.error("saveRecipesToCookbook::Correlation id: {}",correlationId);
        try {
            final RecipesByBookDTO recipesByBookDTO = cookbookService.saveRecipesToBook(id, recipes);
            log.error("saveRecipesToCookbook::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(recipesByBookDTO);
        } catch (Exception e){
            log.error("saveRecipesToCookbook::Failed::Correlation id: {} error: {}", correlationId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/cookbook/{id}")
    public ResponseEntity<Cookbook> updateCookbook(@PathVariable final int id, @RequestBody final CookbookDTO cookBookTitle,@RequestHeader(TRACE_ID) String correlationId){
        log.error("updateCookbook::Correlation id: {}",correlationId);
        try {
            final Cookbook cookbook = cookbookService.updateCookBook(id, cookBookTitle);
            log.error("updateCookbook::Executed::Correlation id: {}", correlationId);
            return ResponseEntity.ok(cookbook);
        } catch (Exception e){
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
