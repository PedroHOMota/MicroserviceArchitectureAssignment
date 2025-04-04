package ie.tus.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.RecipesByBookDTO;
import ie.tus.entities.Cookbook;
import ie.tus.services.CookbookService;
import jakarta.inject.Inject;

@RestController
public class CookBookController {


    CookbookService cookbookService;


    @Inject
    public CookBookController(CookbookService cookbookService){
        this.cookbookService = cookbookService;
    }

    @GetMapping("/cookbook")
    public ResponseEntity<HashMap<Integer,String>> cookbook(){
        final HashMap<Integer, String> allCookbooks = cookbookService.getAllCookbooks();
        return ResponseEntity.ok(allCookbooks);
    }

    @GetMapping("/cookbook/{id}")
    public ResponseEntity<HashMap<Integer,String>> cookbook(@PathVariable final int id){
        final HashMap<Integer, String> allCookbooks = cookbookService.getAllCookbooks();
        return ResponseEntity.ok(allCookbooks);
    }

    @GetMapping("/cookbook/{id}/recipes")
    public ResponseEntity<RecipesByBookDTO> getCookbookRecipes(@PathVariable final int id){
        return ResponseEntity.ok(cookbookService.getRecipesForBook(id));
    }

    @DeleteMapping("/cookbook/{id}")
    public ResponseEntity<String> deleteCookBook(@PathVariable final int id){
        cookbookService.deleteCookBook(id);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("/cookbook/{id}/recipes")
    public ResponseEntity<String> deleteCookBookRecipes(@PathVariable final int id){
        //send to second one

        //cookbookService.getRecipes(id);
        return ResponseEntity.ok("0recipes");
    }

    @PostMapping("/cookbook")
    public ResponseEntity<Cookbook> createCookbook(@RequestParam("bookName") final String cookBookTitle){
        return ResponseEntity.ok(cookbookService.createCookBook(cookBookTitle));
    }

    @PostMapping("/cookbook/{id}")
    public ResponseEntity<RecipesByBookDTO> saveRecipesToCookbook(@PathVariable final int id, @RequestBody final AddRecipesToBook recipes){
        return ResponseEntity.ok(cookbookService.saveRecipesToBook(id,recipes));
    }

    @PutMapping("/cookbook/{id}")
    public ResponseEntity<Cookbook> updateCookbook(@PathVariable final int id, @RequestParam("bookName") final String cookBookTitle){
        return ResponseEntity.ok(cookbookService.updateCookBook(id,cookBookTitle));
    }

}
