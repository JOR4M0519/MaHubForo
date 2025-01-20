package co.edu.glm.mahubforo.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocs {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .components(new Components()
        .addSecuritySchemes("bearer-key",
        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(new Info()
                        .title("Mahub API")
                        .description("Plataforma Mahub es una API Rest diseñada para gestionar discusiones y temas." +
                                " Ofrece la creación de usuarios, tópicos y respuestas. " +
                                "Actualmente, incluye operaciones CRUD para las entidades de respuestas y tópicos.")
                        .contact(new Contact()
                                .name("Jeanpierr Ramos")
                                .url("https://www.linkedin.com/in/jeanpierr-ramos/"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
