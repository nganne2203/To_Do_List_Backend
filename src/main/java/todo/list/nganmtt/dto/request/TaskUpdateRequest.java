package todo.list.nganmtt.dto.request;

import jakarta.validation.constraints.Future;
import lombok.*;
import lombok.experimental.FieldDefaults;
import todo.list.nganmtt.validator.RequiredField;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskUpdateRequest {
    @RequiredField
    String title;
    String description;
    @Future(message = "DUE_DATE_VALIDATION")
    @RequiredField
    LocalDate dueDate;
    boolean completed;
}
