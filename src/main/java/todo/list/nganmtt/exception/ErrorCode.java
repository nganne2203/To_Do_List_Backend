package todo.list.nganmtt.exception;

public enum ErrorCode {

    USER_NAME_ALREADY_EXISTS(1001, "Username already exists"),
    EMAIL_ALREADY_EXISTS(1002, "Email already exists"),
    USER_NOT_FOUND(1003, "User not found"),
    INVALID_CREDENTIALS(1004, "Invalid username or password"),
    UNAUTHORIZED(1005, "Unauthorized access"),
    UNCAUGHT_EXCEPTION(1006, "An unexpected error occurred"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
