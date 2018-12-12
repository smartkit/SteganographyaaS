package info.smartkit.saas.steganographyaas;

import com.shekhargulati.reactivex.docker.client.RxDockerClient;
import com.shekhargulati.reactivex.docker.client.representations.DockerVersion;
import info.smartkit.saas.steganographyaas.domain.Car;
import info.smartkit.saas.steganographyaas.service.CarService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SteganographyaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SteganographyaasApplication.class, args);

		//https://github.com/shekhargulati/rx-docker-client
		//Create a new Docker client using DOCKER_HOST and DOCKER_CERT_PATH environment variables
		RxDockerClient client = RxDockerClient.fromDefaultEnv();

		// Getting Docker version
		DockerVersion dockerVersion = client.serverVersion();
		System.out.println(dockerVersion.version()); // 1.8.3
	}


	@Bean
	ApplicationRunner init(CarService carService) {
		return args -> {
			Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
					"AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
				Car car = new Car();
				car.setName(name);
				carService.saveCar(car);
			});
			carService.getCars().forEach(System.out::println);
		};
	}
}
