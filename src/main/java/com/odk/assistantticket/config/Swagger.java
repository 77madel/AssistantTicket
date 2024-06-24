package com.odk.assistantticket.config;




//import io.swagger.models.Contact;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("AssistantTicket")
                        .description("Api de Gestion de Ticket au sein de Kalanso")
                        .version("0.0.1")
                        .termsOfService("http://swagger.io/terms/")
                        //.contact(new Contact().name("Madou").email("madoumadeltitokone77@gmail.com"))
                        //.license(new License().name("Apache 2.0").url("http://springdoc.org")/)

                );
    }
}
