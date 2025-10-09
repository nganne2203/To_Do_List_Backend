package todo.list.nganmtt.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Constraint(
        validatedBy = {RequiredFieldValidator.class}
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredField {
    String message() default "FIELD_REQUIRED";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
