package application.impacto_manager_back.config.openApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface AlunoDocs {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AlunoOperation(summary = "Listar todos os alunos", description = "Retorna uma lista paginada de todos os alunos cadastrados")
    @SwaggerDocs.DefaultResponses
    @interface BuscarTodos {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AlunoOperation(summary = "Buscar aluno por ID", description = "Retorna os detalhes de um aluno específico")
    @SwaggerDocs.DefaultResponses
    @interface BuscarPorId {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AlunoOperation(summary = "Buscar aluno por nome", description = "Retorna os detalhes de um aluno específico")
    @SwaggerDocs.DefaultResponses
    @interface BuscarPorNome {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AlunoOperation(summary = "Buscar aluno por cpf", description = "Retorna os detalhes de um aluno específico")
    @SwaggerDocs.DefaultResponses
    @interface BuscarPorCPF {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AlunoOperation(summary = "Criar novo aluno", description = "Cadastra um novo aluno no sistema")
    @SwaggerDocs.DefaultResponses
    @interface Criar {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AlunoOperation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente")
    @SwaggerDocs.DefaultResponses
    @interface Atualizar {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AlunoOperation(summary = "Excluir aluno", description = "Remove um aluno do sistema")
    @SwaggerDocs.DefaultResponses
    @interface Excluir {}
}
