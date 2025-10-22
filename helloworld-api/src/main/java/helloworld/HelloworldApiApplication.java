package helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloworldApiApplication {
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		SpringApplication.run(HelloworldApiApplication.class, args);
	}
}
