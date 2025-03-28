package application.impacto_manager_back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Error> handleCustomException(CustomException ex) {
        Error error = ex.getErrors().isEmpty() ?
            Error.builder()
                .title("Erro Personalizado")
                .message(ex.getMessage())
                .httpStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .build() :
            ex.getErrors().get(0);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(Exception ex) {
        Error error = Error.builder()
            .title("Erro Genérico")
            .message("Erro interno no servidor")
            .httpStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
            .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}