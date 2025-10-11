package todo.list.nganmtt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import todo.list.nganmtt.validator.RequiredField;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
        description = "Request object for user update",
        title = "UserUpdateRequest",
        requiredProperties = {
            "username",
            "email"
        },
        example = """
        {
            "username": "johndoe",
            "email": "abc@gmail.com"
        }
        """
)
public class UserUpdateRequest {
    @RequiredField
    @Size(min = 3, max = 50, message = "INVALID_USERNAME")
    String username;

    @RequiredField
    @Email(message = "INVALID_EMAIL")
    String email;
}
