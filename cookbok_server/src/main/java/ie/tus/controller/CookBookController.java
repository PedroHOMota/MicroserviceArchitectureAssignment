package ie.tus.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

import ie.tus.services.CookbookService;
import jakarta.inject.Inject;

@RestController
public class CookBookController {

    CookbookService cookbookService;


    @Inject
    public CookBookController(CookbookService cookbookService){
        this.cookbookService = cookbookService;
    }

    @GetMapping("/a")
    public ResponseEntity<String> main(){
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/b")
    public String eureka(){
        return String.format(
            "Hello from1!");
    }

    @GetMapping("/cookbook")
    public ResponseEntity<HashMap<Integer,String>> cookbook(){
        final HashMap<Integer, String> allCookbooks = cookbookService.getAllCookbooks();
        return ResponseEntity.ok(allCookbooks);
    }

    @GetMapping("/cookbook/{id}/recipes")
    public ResponseEntity<String> cookbookRecipes(@PathVariable int id){
        //send to second one
        return ResponseEntity.ok("recipes");
    }

    @DeleteMapping("/cookbook/{id}")
    public ResponseEntity<String> deleteCookBook(@PathVariable int id){
        //delete cookBook
        return ResponseEntity.ok("recipes");
    }

    @DeleteMapping("/cookbook/{id}/recipes")
    public ResponseEntity<String> deleteCookBookRecipes(@PathVariable int id){
        //send to second one
        return ResponseEntity.ok("recipes");
    }
}
