package todo.list.nganmtt.exception;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import todo.list.nganmtt.dto.request.ApiResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";
    private static final String MAX_ATTRIBUTE = "max";
    private static final String NAME_ATTRIBUTE = "name";

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    ResponseEntity<ApiResult<?>> handlingMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(ApiResult.builder()
                        .code(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getCode())
                        .message(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getMessage())
                        .build());
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiResult<?>> handlingNoResourceFoundException(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResult.builder()
                        .code(ErrorCode.RESOURCE_NOT_FOUND.getCode())
                        .message(ErrorCode.RESOURCE_NOT_FOUND.getMessage())
                        .build());
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResult<?>> handlingRunTimeException(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(ApiResult.builder()
                        .code(1001)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResult<?>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResult.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResult<?>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var fieldError = Objects.requireNonNull(ex.getBindingResult().getFieldError());
        String enumKey = fieldError.getDefaultMessage();
        ErrorCode errorCode = ErrorCode.KEY_VALID;
        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constraintViolation = fieldError.unwrap(ConstraintViolation.class);
            attributes = new HashMap<>(constraintViolation.getConstraintDescriptor().getAttributes());
            attributes.put("name", ex.getBindingResult().getFieldError().getField());

            log.info(attributes.toString());
        } catch (IllegalArgumentException e) {
            log.error("Invalid enum key: {}", enumKey);
        }

        return ResponseEntity.badRequest()
                .body(ApiResult.builder()
                        .code(errorCode.getCode())
                        .message(Objects.nonNull(attributes) ? mapAttribute(errorCode.getMessage(), attributes) : errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResult<?>> handlingException(Exception exception) {
        return ResponseEntity.badRequest()
                .body(ApiResult.builder()
                        .code(1001)
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResult<?>> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ApiResult.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    private String mapAttribute (String message, Map<String, Object> attributes){
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        String maxValue = String.valueOf(attributes.get(MAX_ATTRIBUTE));

        String nameValue = String.valueOf(attributes.get(NAME_ATTRIBUTE));

        return message
                .replace("{" + MIN_ATTRIBUTE + "}", minValue)
                .replace("{" +MAX_ATTRIBUTE + "}", maxValue)
                .replace("{" + NAME_ATTRIBUTE + "}", nameValue)
                ;
    }
}
