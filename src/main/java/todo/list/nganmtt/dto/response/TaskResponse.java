package todo.list.nganmtt.dto.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
        description = "Response object for task details",
        title = "TaskResponse"
)
public class TaskResponse {
    String id;
    String title;
    String description;
    @JsonFormat(pattern = "HH:mm, dd/MM/yyyy")
    LocalDateTime dueDate;
    boolean completed;
    @JsonFormat(pattern = "HH:mm, dd/MM/yyyy")
    LocalDateTime createdAt;
}
