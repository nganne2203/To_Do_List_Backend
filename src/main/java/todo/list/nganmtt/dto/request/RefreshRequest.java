package todo.list.nganmtt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
        description = "Request object for token refresh",
        title = "RefreshRequest",
        requiredProperties = {
                "token"
        },
        example = """
        {
            "token": "string"
        }
        """
)
public class RefreshRequest {
    String token;
}
