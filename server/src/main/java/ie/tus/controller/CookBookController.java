package ie.tus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RestController
public class CookBookController {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/a")
    public ResponseEntity<String> main(){
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/b")
    public String eureka(){
        return String.format(
            "Hello from1 '%s'!", eurekaClient.getApplication(appName).getName());
    }

    @GetMapping("/cookbook")
    public ResponseEntity<String> cookbook(){
        return ResponseEntity.ok("cookbook");
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
