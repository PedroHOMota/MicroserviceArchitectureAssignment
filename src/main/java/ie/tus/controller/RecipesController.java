package ie.tus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/tst")
    public ResponseEntity tst(){

        Recipe recipe = new Recipe();
        recipe.setRecipe("Make a burguer");
        recipe.setTitle("Burguer Recipe");
        recipe.setAuthor("MyAuthor");
        recipe.setIngredients("Minced meat");

        Recipe another = new Recipe();
        another.setRecipe("Make a burguer");
        another.setTitle("Another burguer");
        another.setAuthor("MyAuthor");
        another.setIngredients("Minced meat");

        //recipesService.saveRecipe(recipe);
        //recipesService.saveRecipe(another);

        AddRecipesToBook addRecipesToBook = new AddRecipesToBook();
        addRecipesToBook.setRecipesIds(new int[] {2,1});
        addRecipesToBook.setBookId(1);

        recipesService.saveRecipesToBook(addRecipesToBook);

        final RecipesByBook recipesBook = recipesService.getRecipesBook(1);

        return ResponseEntity.ok(recipesBook);
    }

    @GetMapping("/recipes/byBook/{bookId}")
    public ResponseEntity getCookbookRecipes(@PathVariable int bookId){
        final RecipesByBook recipesBook = recipesService.getRecipesBook(1);

        return ResponseEntity.ok(recipesBook);
    }

    @GetMapping("/recipes")
    public ResponseEntity getRecipe(@PathVariable int id){
        final Recipe recipe = recipesService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id){
        recipesService.deleteRecipe(id);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("/recipes/byBook/{bookId}")
    public ResponseEntity deleteCookBookRecipes(@PathVariable int id){
        recipesService.deleteRecipesBook(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("/recipes")
    public ResponseEntity createRecipe(@PathVariable int id, RecipeDTO recipe){
        recipesService.saveRecipe(recipe);
        return ResponseEntity.ok("saved");
    }

    @PostMapping("/recipes/byBook")
    public ResponseEntity saveRecipesToBook(@RequestBody AddRecipesToBook addRecipesToBook){
        recipesService.saveRecipesToBook(addRecipesToBook);
        return ResponseEntity.ok("recipes");
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity updateRecipe(@PathVariable int id, @RequestBody RecipeDTO dto){
        recipesService.updateRecipe(id, dto);
        return ResponseEntity.ok("updated");
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity addRecipeToBook(@PathVariable int id, @RequestBody AddRecipesToBook dto){
        recipesService.addRecipeToBook(id, dto.getRecipesIds());
        return ResponseEntity.ok("updated");
    }


}
