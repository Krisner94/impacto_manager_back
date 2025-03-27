package application.impacto_manager_back.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.Error;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private final List<java.lang.Error> errors = new ArrayList<>();

    public CustomException(Class<?> clazz) {
        super("Erro na classe " + clazz.getSimpleName());
    }

    public CustomException(String error, ReflectiveOperationException e) {
        super(error, e);
    }

    public void addErrors(List<java.lang.Error> errors) {
        this.errors.addAll(errors);
    }

    public CustomException addError(Error error) {
        this.errors.add(error);
        return this;
    }

    public CustomException(String mesage){
        super(mesage);
    }

}