package application.impacto_manager_back.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionBuildMessage {
    public static <ID> Error errorBuildMessage(Class<?> clazz, ID id) {
        return Error.builder()
            .title(clazz.getSimpleName() + " não encontrado")
            .message(clazz.getSimpleName() + " com o ID " + id.toString() + " não foi encontrado.")
            .httpStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
            .build();
    }

    public static <ID> Error errorBuildMessage(ID id) {
        return Error.builder()
            .title("Id não encontrado")
            .message("Não existe objeto no sistema com id " + id.toString())
            .httpStatus(String.valueOf(HttpStatus.NOT_FOUND.value()))
            .build();
    }
}
