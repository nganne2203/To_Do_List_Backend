package todo.list.nganmtt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import todo.list.nganmtt.dto.request.ApiResult;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResult<?>> handlingRunTimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ApiResult.error(1001, ex.getMessage()));
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResult<?>> handlingAppException(AppException exception) {
        return ResponseEntity.badRequest().body(ApiResult.error(exception.getErrorCode()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResult<?>> handlingException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(ApiResult.error(ErrorCode.UNCAUGHT_EXCEPTION));
    }

}
