package br.com.diazero.incidents.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class IncidentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentsApplication.class, args);
	}

}
