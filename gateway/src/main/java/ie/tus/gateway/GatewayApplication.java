package ie.tus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Mono;

@SpringBootApplication
//@EnableFeignClients
//@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator mainRoute(RouteLocatorBuilder routeLocatorBuilder){
//		return routeLocatorBuilder.routes().route(predicate ->
//			predicate.path("/cookbookapp/c/*")
//				.filters(f -> f.rewritePath("/cookbookapp/c/(?<segment>.*)","/${segment}")
//					.circuitBreaker(config -> config.setName("cookbookCircuitBreaker")
//						.setFallbackUri("forward:/recipes/all"))
//				)
//				.uri("lb://COOKBOOK")
//		).route(predicate ->
//			predicate.path("/cookbookapp/r/**")
//				.filters(f -> f.rewritePath("/cookbookapp/r/(?<segment>.*)","/${segment}")
//					.circuitBreaker(config -> config.setName("")
//						.setFallbackUri("forward:/contactSupport"))
//				)
//				.uri("lb://RECIPES")).build();
//	}

	@Bean
	public RouteLocator mainRoute(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes().route(predicate ->
			predicate.path("/cookbook/**").filters(f -> f.circuitBreaker(config -> config.setName("")
					.setFallbackUri("forward:/contactSupport")))
				.uri("lb://COOKBOOK")
		).route(predicate ->
			predicate.path("/recipes/**").filters(f -> f.circuitBreaker(config -> config.setName("")
				.setFallbackUri("forward:/contactSupport"))).uri("lb://RECIPES")).build();
	}

//	@Bean
//	KeyResolver userKeyResolver() {
//		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
//			.defaultIfEmpty("anonymous");
//	}

}
