package ie.tus.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.RecipeDTO;
import ie.tus.entities.Recipe;
import ie.tus.entities.RecipesByBook;
import ie.tus.services.RecipesService;

@RestController
public class RecipesController {

    public static final String TRACE_ID = "cookbookap-trace-id";

    private static final Logger log = LoggerFactory.getLogger(RecipesController.class);
    @Autowired
    RecipesService recipesService;

    @GetMapping("/recipes/byBook/{bookId}")
    public ResponseEntity<RecipesByBook> getCookbookRecipes(@PathVariable int bookId, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("getCookbookRecipes::Correlation id: {}",correlationId);
        final RecipesByBook recipesBook = recipesService.getRecipesBook(bookId);
        log.debug("getCookbookRecipes::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(recipesBook);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("getRecipe::Correlation id: {}",correlationId);
        final Recipe recipe = recipesService.getRecipe(id);
        log.debug("getRecipe::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/recipes/all")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestHeader(TRACE_ID) String correlationId){
        log.debug("getRecipes::Correlation id: {}",correlationId);
        final List<Recipe> allRecipes = recipesService.getAllRecipe();
        log.debug("getRecipes::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(allRecipes);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("deleteRecipe::Correlation id: {}",correlationId);
        recipesService.deleteRecipe(id);
        log.debug("deleteRecipe::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("/recipes/byBook/{bookId}")
    public ResponseEntity deleteCookBook(@PathVariable int id, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("deleteCookBook::Correlation id: {}",correlationId);
        recipesService.deleteRecipesBook(id);
        log.debug("deleteCookBook::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok("deleted");
    }

    @PatchMapping("/recipes/byBook/{bookId}")
    public ResponseEntity deleteCookBookRecipes(@PathVariable int id, AddRecipesToBook addRecipesToBook, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("deleteCookBookRecipes::Correlation id: {}",correlationId);
        //recipesService.deleteRecipesFromBook(id);
        log.debug("deleteCookBookRecipes::Executed::Correlation id: {}",correlationId);

        return ResponseEntity.ok("deleted");
    }

    @PostMapping("/recipes")
    public ResponseEntity createRecipe(@RequestBody RecipeDTO recipe, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("createRecipe::Correlation id: {}",correlationId);
        final Recipe recipe1 = recipesService.saveRecipe(recipe);
        log.debug("createRecipe::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(recipe1);

    }

    @PostMapping("/recipes/byBook")
    public ResponseEntity saveRecipesToBook(@RequestBody AddRecipesToBook addRecipesToBook, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("saveRecipesToBook::Correlation id: {}",correlationId);
        final RecipesByBook recipesByBook = recipesService.saveRecipesToBook(addRecipesToBook);
        log.debug("saveRecipesToBook::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(recipesService.saveRecipesToBook(addRecipesToBook));
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity updateRecipe(@PathVariable int id, @RequestBody RecipeDTO dto, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("updateRecipe::Correlation id: {}",correlationId);
        final Recipe recipe = recipesService.updateRecipe(id, dto);
        log.debug("updateRecipe::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/recipes/byBook/{id}")
    public ResponseEntity updateCookbook(@PathVariable int id, @RequestBody AddRecipesToBook dto, @RequestHeader(TRACE_ID) String correlationId){
        log.debug("updateCookbook::Correlation id: {}",correlationId);
        final RecipesByBook recipesByBook = recipesService.addRecipeToBook(id, dto.getRecipesIds());
        log.debug("updateCookbook::Executed::Correlation id: {}",correlationId);

        return ResponseEntity.ok(recipesByBook);
    }


}
