package ie.tus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages={ "ie.tus"})
@EnableDiscoveryClient
public class RecipesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesServerApplication.class, args);
	}

}
