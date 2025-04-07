package application.impacto_manager_back.config.openApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@io.swagger.v3.oas.annotations.Operation
public @interface Operation {
    String summary();
    String description() default "";
}
