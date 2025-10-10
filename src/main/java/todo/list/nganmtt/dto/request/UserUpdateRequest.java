package todo.list.nganmtt.dto.request;

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
public class UserUpdateRequest {
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
