package io.github.cciglesiasmartinez.microservice_template;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Este es el punto de entrada de la aplicación, y anotaremos tanto {@code OpenAPIDefinition} para la documentación
 * de Swagger, como {@code EntityScan} y {@code EnableJpaRepositories} para indicar donde se encuentran las
 * implementaciones de los repositorios MySQL/JPA y las entidades correspondientes.
 */
@OpenAPIDefinition(
		info = @Info(title = "Item service API", version = "v1")
)
@SpringBootApplication
@EnableJpaRepositories(basePackages = "io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository")
@EntityScan(basePackages = "io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity")
public class MicroserviceTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTemplateApplication.class, args);
	}

}
