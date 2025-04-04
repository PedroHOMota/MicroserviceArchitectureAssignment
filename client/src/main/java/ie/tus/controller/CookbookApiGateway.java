package ie.tus.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.Cookbook;
import ie.tus.DTO.RecipeDTO;
import ie.tus.DTO.RecipesByBookDTO;

@FeignClient(value = "cookbook")
public interface CookbookApiGateway {
    @GetMapping(value = "/cookbook", consumes = "application/json")
    public ResponseEntity<HashMap<Integer,String>> cookbook();

    @GetMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<HashMap<Integer,String>> cookbook(@PathVariable final int id);

    @GetMapping(value = "/cookbook/{id}/recipes", consumes = "application/json")
    public ResponseEntity<RecipesByBookDTO> getCookbookRecipes(@PathVariable final int id);

    @DeleteMapping(value = "/cookbook/{id}")
    public ResponseEntity<String> deleteCookBook(@PathVariable final int id);

    @DeleteMapping(value = "/cookbook/{id}/recipes")
    public ResponseEntity<String> deleteCookBookRecipes(@PathVariable final int id);

    @PostMapping(value = "/cookbook", consumes = "application/json")
    public ResponseEntity<Cookbook> createCookbook(@RequestParam("bookName") final String cookBookTitle);

    @PostMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<RecipesByBookDTO> saveRecipesToCookbook(@PathVariable final int id, @RequestBody final AddRecipesToBook recipes);

    @PutMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<Cookbook> updateCookbook(@PathVariable final int id, @RequestParam("bookName") final String cookBookTitle);

    @GetMapping(value = "/recipes/{id}", consumes = "application/json")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable int id);

    @GetMapping(value = "/recipes/all", consumes = "application/json")
    public ResponseEntity<List<RecipeDTO>> getRecipes();

    @PostMapping(value = "/recipes", consumes = "application/json")
    public ResponseEntity createRecipe(@RequestBody RecipeDTO recipe);

    @PutMapping(value = "/recipes/update/{id}", consumes = "application/json")
    public ResponseEntity updateRecipe(@PathVariable int id, @RequestBody RecipeDTO dto);
}
