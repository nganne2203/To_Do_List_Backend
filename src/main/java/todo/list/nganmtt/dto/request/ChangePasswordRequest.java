package todo.list.nganmtt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import todo.list.nganmtt.validator.RequiredField;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
        description = "Request object for changing user password",
        title = "ChangePasswordRequest",
        requiredProperties = {
            "currentPassword",
            "newPassword"
        },
        example = """
        {
            "currentPassword": "OldPassword123!",
            "newPassword": "NewPassword456!"
        }
        """
)
public class ChangePasswordRequest {
    @RequiredField
    String currentPassword;
    @RequiredField
    String newPassword;
}
