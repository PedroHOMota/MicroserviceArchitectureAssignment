package ie.tus.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.tus.DTO.AddRecipesToBook;
import ie.tus.DTO.RecipesByBookDTO;
import ie.tus.util.entities.Cookbook;
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
    public ResponseEntity<HashMap<Integer,String>> getAllCookbooks(@RequestHeader(TRACE_ID) String correlationId){
        log.error("getAllCookbooks::Correlation id: {}",correlationId);
        final HashMap<Integer, String> allCookbooks = cookbookService.getAllCookbooks();
        log.error("getAllCookbooks::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(allCookbooks);
    }

    @GetMapping("/cookbook/{id}")
    public ResponseEntity<HashMap<Integer,String>> getCookbook(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("getCookbook::Correlation id: {}",correlationId);
        final HashMap<Integer, String> allCookbooks = cookbookService.getAllCookbooks();
        log.error("getCookbook::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(allCookbooks);
    }

    @GetMapping("/cookbook/{id}/recipes")
    public ResponseEntity<RecipesByBookDTO> getCookbookRecipes(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("getCookbookRecipes::Correlation id: {}",correlationId);
        final RecipesByBookDTO recipesForBook = cookbookService.getRecipesForBook(id);
        log.error("getCookbookRecipes::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(recipesForBook);
    }

    @DeleteMapping("/cookbook/{id}")
    public ResponseEntity<String> deleteCookBook(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("deleteCookBook::Correlation id: {}",correlationId);
        cookbookService.deleteCookBook(id);
        log.error("deleteCookBook::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("/cookbook/{id}/recipes")
    public ResponseEntity<String> deleteCookBookRecipes(@PathVariable final int id,@RequestHeader(TRACE_ID) String correlationId){
        log.error("deleteCookBookRecipes::Correlation id: {}",correlationId);
        log.error("deleteCookBookRecipes::Executed::Correlation id: {}",correlationId);
        //send to second one

        //cookbookService.getRecipes(id);
        return ResponseEntity.ok("0recipes");
    }

    @PostMapping("/cookbook")
    public ResponseEntity<Cookbook> createCookbook(@RequestParam("bookName") final String cookBookTitle,@RequestHeader(TRACE_ID) String correlationId){
        log.error("createCookbook::Correlation id: {}",correlationId);
        System.out.println("createCookbook::Correlation id: "+correlationId);
        final Cookbook cookBook = cookbookService.createCookBook(cookBookTitle);
        log.error("createCookbook::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(cookBook);
    }

    @PostMapping("/cookbook/{id}")
    public ResponseEntity<RecipesByBookDTO> saveRecipesToCookbook(@PathVariable final int id, @RequestBody final AddRecipesToBook recipes,@RequestHeader(TRACE_ID) String correlationId){
        log.error("saveRecipesToCookbook::Correlation id: {}",correlationId);
        final RecipesByBookDTO recipesByBookDTO = cookbookService.saveRecipesToBook(id, recipes);
        log.error("saveRecipesToCookbook::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(recipesByBookDTO);
    }

    @PutMapping("/cookbook/{id}")
    public ResponseEntity<Cookbook> updateCookbook(@PathVariable final int id, @RequestParam("bookName") final String cookBookTitle,@RequestHeader(TRACE_ID) String correlationId){
        log.error("updateCookbook::Correlation id: {}",correlationId);
        final Cookbook cookbook = cookbookService.updateCookBook(id, cookBookTitle);
        log.error("updateCookbook::Executed::Correlation id: {}",correlationId);
        return ResponseEntity.ok(cookbook);
    }

}
