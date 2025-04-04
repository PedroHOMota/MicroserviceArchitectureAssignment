package ie.tus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.RecipeDTO;
import ie.tus.entities.Recipe;
import ie.tus.entities.RecipesByBook;
import ie.tus.services.RecipesService;

@RestController
public class RecipesController {

    @Autowired
    RecipesService recipesService;

    @GetMapping("/recipes/byBook/{bookId}")
    public ResponseEntity<RecipesByBook> getCookbookRecipes(@PathVariable int bookId){
        final RecipesByBook recipesBook = recipesService.getRecipesBook(bookId);

        return ResponseEntity.ok(recipesBook);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id){
        final Recipe recipe = recipesService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/recipes/all")
    public ResponseEntity<List<Recipe>> getRecipes(){
        final List<Recipe> allRecipes = recipesService.getAllRecipe();
        return ResponseEntity.ok(allRecipes);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id){
        recipesService.deleteRecipe(id);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("/recipes/byBook/{bookId}")
    public ResponseEntity deleteCookBook(@PathVariable int id){
        recipesService.deleteRecipesBook(id);
        return ResponseEntity.ok("deleted");
    }

    @PatchMapping("/recipes/byBook/{bookId}")
    public ResponseEntity deleteCookBookRecipes(@PathVariable int id, AddRecipesToBook addRecipesToBook){
        //recipesService.deleteRecipesFromBook(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("/recipes")
    public ResponseEntity createRecipe(@RequestBody RecipeDTO recipe){
        return ResponseEntity.ok(recipesService.saveRecipe(recipe));
    }

    @PostMapping("/recipes/byBook")
    public ResponseEntity saveRecipesToBook(@RequestBody AddRecipesToBook addRecipesToBook){
        return ResponseEntity.ok(recipesService.saveRecipesToBook(addRecipesToBook));
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity updateRecipe(@PathVariable int id, @RequestBody RecipeDTO dto){
        return ResponseEntity.ok(recipesService.updateRecipe(id, dto));
    }

    @PutMapping("/recipes/byBook/{id}")
    public ResponseEntity updateCookbook(@PathVariable int id, @RequestBody AddRecipesToBook dto){

        return ResponseEntity.ok(recipesService.addRecipeToBook(id, dto.getRecipesIds()));
    }


}
