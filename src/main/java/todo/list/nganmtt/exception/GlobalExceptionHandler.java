package todo.list.nganmtt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.validation.FieldError;
import todo.list.nganmtt.dto.request.ApiResult;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResult<?>> handlingValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(
            ApiResult.error(1002, "Validation failed: " + errors.toString())
        );
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    ResponseEntity<ApiResult<?>> handlingMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error("Media type not supported: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
            ApiResult.error(1003, "Content-Type not supported. Please use application/json")
        );
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiResult<?>> handlingNoResourceFoundException(NoResourceFoundException ex) {
        log.error("No static resource found: {}", ex.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResult<?>> handlingRunTimeException(RuntimeException ex) {
        log.error("Runtime exception: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ApiResult.error(1001, ex.getMessage()));
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResult<?>> handlingAppException(AppException exception) {
        log.warn("Application exception: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(ApiResult.error(exception.getErrorCode()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResult<?>> handlingException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(ApiResult.error(ErrorCode.UNCAUGHT_EXCEPTION));
    }
}
