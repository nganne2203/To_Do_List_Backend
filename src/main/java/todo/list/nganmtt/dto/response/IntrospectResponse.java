package todo.list.nganmtt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
        description = "Response object for token introspection results",
        title = "IntrospectResponse"
)
public class IntrospectResponse {
    boolean valid;
}
