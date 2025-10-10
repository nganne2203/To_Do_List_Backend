package todo.list.nganmtt.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TaskUpdateRequest {
    @RequiredField
    String title;
    String description;
    @JsonFormat(pattern = "HH:mm, dd/MM/yyyy")
    @FutureOrPresent(message = "DUE_DATE_VALIDATION")
    @NotNull(message = "DUE_DATE_REQUIRED")
    LocalDateTime dueDate;
    boolean completed;
}
