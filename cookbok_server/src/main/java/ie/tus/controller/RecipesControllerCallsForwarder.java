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
import ie.tus.DTO.RecipesByBookDTO;
import ie.tus.services.RecipeForwarderService;

@RestController
public class RecipesControllerCallsForwarder {

    @Autowired
    RecipeForwarderService recipesService;

    @GetMapping("/recipes/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable int id){
        final RecipeDTO recipe = recipesService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/recipes/all")
    public ResponseEntity<List<RecipeDTO>> getRecipes(){
        final List<RecipeDTO> allRecipes = recipesService.getAllRecipes();
        return ResponseEntity.ok(allRecipes);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id){
        //recipesService.deleteRecipe(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("/recipes")
    public ResponseEntity createRecipe(@RequestBody RecipeDTO recipe){
        return ResponseEntity.ok(recipesService.saveRecipe(recipe));
    }

    @PutMapping("/recipes/update/{id}")
    public ResponseEntity updateRecipe(@PathVariable int id, @RequestBody RecipeDTO dto){
        return ResponseEntity.ok(recipesService.updateRecipe(id, dto));
    }

}
