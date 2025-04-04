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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.Cookbook;
import ie.tus.DTO.RecipeDTO;
import ie.tus.DTO.RecipesByBookDTO;

@FeignClient(value = "cookbook")
public interface CookbookApiFeign {
    public static final String TRACE_ID = "cookbookap-trace-id";

    @GetMapping(value = "/cookbook", consumes = "application/json")
    public ResponseEntity<HashMap<Integer,String>> cookbook(@RequestHeader(TRACE_ID) String correlationId);

    @GetMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<HashMap<Integer,String>> cookbook(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId);

    @GetMapping(value = "/cookbook/{id}/recipes", consumes = "application/json")
    public ResponseEntity<RecipesByBookDTO> getCookbookRecipes(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId);

    @DeleteMapping(value = "/cookbook/{id}")
    public ResponseEntity<String> deleteCookBook(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId);

    @DeleteMapping(value = "/cookbook/{id}/recipes")
    public ResponseEntity<String> deleteCookBookRecipes(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId);

    @PostMapping(value = "/cookbook", consumes = "application/json")
    public ResponseEntity<Cookbook> createCookbook(@RequestParam("bookName") final String cookBookTitle,@RequestHeader(TRACE_ID) String correlationId);

    @PostMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<RecipesByBookDTO> saveRecipesToCookbook(@PathVariable final int id, @RequestBody final AddRecipesToBook recipes,@RequestHeader(TRACE_ID) String correlationId);

    @PutMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<Cookbook> updateCookbook(@PathVariable final int id, @RequestParam("bookName") final String cookBookTitle,@RequestHeader(TRACE_ID) String correlationId);

    @GetMapping(value = "/recipes/{id}", consumes = "application/json")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable int id,@RequestHeader(TRACE_ID) String correlationId);

    @GetMapping(value = "/recipes/all", consumes = "application/json")
    public ResponseEntity<List<RecipeDTO>> getRecipes(@RequestHeader(TRACE_ID) String correlationId);

    @PostMapping(value = "/recipes", consumes = "application/json")
    public ResponseEntity createRecipe(@RequestBody RecipeDTO recipe,@RequestHeader(TRACE_ID) String correlationId);

    @PutMapping(value = "/recipes/update/{id}", consumes = "application/json")
    public ResponseEntity updateRecipe(@PathVariable int id, @RequestBody RecipeDTO dto,@RequestHeader(TRACE_ID) String correlationId);
}
