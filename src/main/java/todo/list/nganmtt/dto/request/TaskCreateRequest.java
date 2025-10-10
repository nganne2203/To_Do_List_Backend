package todo.list.nganmtt.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import todo.list.nganmtt.validator.RequiredField;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
        description = "Request object for creating a new task",
        title = "TaskCreateRequest",
        requiredProperties = {
            "title",
            "dueDate"
        },
        example = """
        {
            "title": "Created Task Title",
            "description": "Updated description of the task.",
            "dueDate": "18:00, 31/12/2024",
            "completed": true
        }
        """
)
public class TaskCreateRequest {
    @RequiredField
    String title;
    String description;
    @FutureOrPresent(message = "DUE_DATE_VALIDATION")
    @JsonFormat(pattern = "HH:mm, dd/MM/yyyy")
    @NotNull(message = "DUE_DATE_REQUIRED")
    LocalDateTime dueDate;
    boolean completed;
}
