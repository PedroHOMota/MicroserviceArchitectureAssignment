package ie.tus.gateway.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value = "cookbook")
public interface RecipesApiFeign {
    public static final String TRACE_ID = "cookbookap-trace-id";

    @GetMapping(value = "/recipes/all", consumes = "application/json")
    public ResponseEntity<List<RecipeDTO>> getRecipes(@RequestHeader(TRACE_ID) String correlationId);

}
