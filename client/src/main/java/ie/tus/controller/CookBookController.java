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
    private TestFeign testFeign;

    @GetMapping("/a")
    public ResponseEntity<String> main(){
        return ResponseEntity.ok("Hello WorldClient!");
    }

    @GetMapping("/b")
    public String eureka(){
        return testFeign.get();
    }
}
