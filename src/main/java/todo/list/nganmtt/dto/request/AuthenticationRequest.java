package todo.list.nganmtt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.Size;
import todo.list.nganmtt.validator.RequiredField;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
        description = "Request object for user authentication",
        title = "AuthenticationRequest",
        requiredProperties = {
            "username",
            "password"
        },
        example = """
        {
            "username": "johndoe",
            "password": "securePassword123"
        }
        """
)
public class AuthenticationRequest {
    @RequiredField
    @Size(min = 3, max = 50, message = "INVALID_USERNAME")
    String username;

    @RequiredField
    String password;
}
