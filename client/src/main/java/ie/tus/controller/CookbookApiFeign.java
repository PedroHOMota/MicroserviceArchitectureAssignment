package ie.tus.controller;

import java.util.HashMap;
import java.util.List;

import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

@FeignClient(value = "GATEWAY")
@Controller
@Headers("Authorization: {Authorization}")
public interface CookbookApiFeign {
    public static final String TRACE_ID = "cookbookap-trace-id";
    public static final String Authorization = "Authorization";

    @GetMapping(value = "/cookbook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cookbook>> cookbook(@RequestHeader(Authorization) String accessToken);

    @GetMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<HashMap<Integer,String>> cookbook(@PathVariable final int id,@RequestHeader(Authorization) String accessToken);

    @GetMapping(value = "/cookbook/{id}/recipes", consumes = "application/json")
    public ResponseEntity<RecipesByBookDTO> getCookbookRecipes(@PathVariable final int id,@RequestHeader(Authorization) String accessToken);

    @DeleteMapping(value = "/cookbook/{id}")
    public ResponseEntity<String> deleteCookBook(@PathVariable final int id,@RequestHeader(Authorization) String accessToken);

    @DeleteMapping(value = "/cookbook/{id}/recipes")
    public ResponseEntity<String> deleteCookBookRecipes(@PathVariable final int id,@RequestHeader(Authorization) String accessToken);

    @PostMapping(value = "/cookbook", consumes = "application/json")
    public ResponseEntity<Cookbook> createCookbook(@RequestParam("bookName") final String cookBookTitle,@RequestHeader(Authorization) String accessToken);

    @PostMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<RecipesByBookDTO> saveRecipesToCookbook(@PathVariable final int id, @RequestBody final AddRecipesToBook recipes,@RequestHeader(Authorization) String accessToken);

    @PutMapping(value = "/cookbook/{id}", consumes = "application/json")
    public ResponseEntity<Cookbook> updateCookbook(@PathVariable final int id, @RequestParam("bookName") final String cookBookTitle,@RequestHeader(Authorization) String accessToken);

    @GetMapping(value = "/recipes/{id}", consumes = "application/json")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable int id, @RequestHeader(Authorization) String accessToken);

    @GetMapping(value = "/recipes/all", consumes = "application/json")
    public ResponseEntity<List<RecipeDTO>> getRecipes(@RequestHeader(Authorization) String accessToken);

    @PostMapping(value = "/recipes", consumes = "application/json")
    public ResponseEntity createRecipe(@RequestBody RecipeDTO recipe, @RequestHeader(Authorization) String accessToken);

    @PutMapping(value = "/recipes/update/{id}", consumes = "application/json")
    public ResponseEntity updateRecipe(@PathVariable int id, @RequestBody RecipeDTO dto, @RequestHeader(Authorization) String accessToken);
}
