package todo.list.nganmtt.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import todo.list.nganmtt.validator.RequiredField;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @RequiredField
    @Size(min = 3, max = 50, message = "INVALID_USERNAME")
    String username;

    @RequiredField
    @Email(message = "INVALID_EMAIL")
    String email;

    @RequiredField
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
}
