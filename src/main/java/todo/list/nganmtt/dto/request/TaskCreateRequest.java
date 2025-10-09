package todo.list.nganmtt.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class TaskCreateRequest {
    String title;
    String description;
    @Future(message = "DUE_DATE_VALIDATION")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dueDate;
    boolean completed;
}
