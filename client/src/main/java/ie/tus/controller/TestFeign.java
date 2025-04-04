package ie.tus.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "API")
public interface TestFeign {
    @GetMapping(value = "/a", consumes = "application/json")
    public String get();
}
