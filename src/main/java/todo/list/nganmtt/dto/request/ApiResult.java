package todo.list.nganmtt.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import todo.list.nganmtt.exception.ErrorCode;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {
    int code = 1000;
    String message;
    T result;

    public static <T> ApiResult<T> error(int code, String message) {
        return ApiResult.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> ApiResult<T> error(ErrorCode errorCode) {
        return ApiResult.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }
}
