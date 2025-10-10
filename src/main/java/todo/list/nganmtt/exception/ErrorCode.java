package todo.list.nganmtt.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NAME_ALREADY_EXISTS(1001, "Username already exists", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(1002, "Email already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1003, "User not found", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS(1004, "Invalid username or password", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1005, "Unauthorized access", HttpStatus.UNAUTHORIZED),
    UNCAUGHT_EXCEPTION(1006, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_VALID(1007, "Invalid message key", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(1008, "Resource not found", HttpStatus.NOT_FOUND),
    UNSUPPORTED_MEDIA_TYPE(1009, "Content-Type not supported. Please use application/json", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    INVALID_USERNAME(1010, "Username must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1011, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    FIELD_REQUIRED(1012, "Field {name} is required", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(1013, "Email should be valid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1014, "Unauthenticated access", HttpStatus.FORBIDDEN),
    TASK_NOT_FOUND(2001, "Task not found", HttpStatus.NOT_FOUND),
    DUE_DATE_VALIDATION(2002, "Due date must be in the future", HttpStatus.BAD_REQUEST),
    DUE_DATE_REQUIRED(2003, "Due date is required", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
