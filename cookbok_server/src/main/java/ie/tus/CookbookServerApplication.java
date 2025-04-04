package ie.tus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CookbookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookbookServerApplication.class, args);
	}

}
