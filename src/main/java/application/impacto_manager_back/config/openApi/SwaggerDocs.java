package application.impacto_manager_back.config.openApi;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface SwaggerDocs {

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", ref = "#/components/responses/badRequest"),
        @ApiResponse(responseCode = "401", description = "Não autorizado", ref = "#/components/responses/unauthorized"),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", ref = "#/components/responses/notFound"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", ref = "#/components/responses/internalError")
    })
    @interface DefaultResponses {}

    @Target(ElementType.ANNOTATION_TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface OperationConfig {
        String[] tags() default {};
        String summary() default "";
        String description() default "";
    }
}