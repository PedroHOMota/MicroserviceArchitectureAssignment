package ie.tus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator mainRoute(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes().route(predicate ->
			predicate.path("/cookbook/**")
				//.filters(f -> f.rewritePath())
				.uri("lb://COOKBOOK")
		).build();
	}

}
