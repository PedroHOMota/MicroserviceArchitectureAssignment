package ie.tus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication(scanBasePackages={ "ie.tus"})
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
@EnableDiscoveryClient
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
