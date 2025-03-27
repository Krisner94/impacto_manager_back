package application.impacto_manager_back.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Impacto Manager")
                .version("v1")
                .description("Sistema de gerenciamento de escola de esportes")
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0")))
            .components(new Components()
                // Respostas padrão reutilizáveis
                .addResponses("badRequest", createApiResponse("BAD REQUEST"))
                .addResponses("unauthorized", createApiResponse("UNAUTHORIZED"))
                .addResponses("notFound", createApiResponse("NOT FOUND"))
                .addResponses("internalError", createApiResponse("INTERNAL ERROR"))

                // Configuração de segurança (se aplicável)
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }

    private ApiResponse createApiResponse(String description) {
        return new ApiResponse().description(description);
    }
}