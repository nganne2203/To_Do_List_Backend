package todo.list.nganmtt.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
    String id;
    String title;
    String description;
    LocalDate dueDate;
    boolean completed;
    LocalDateTime createdAt;
}
