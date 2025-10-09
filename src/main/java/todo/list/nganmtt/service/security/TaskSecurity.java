package todo.list.nganmtt.service.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import todo.list.nganmtt.exception.AppException;
import todo.list.nganmtt.exception.ErrorCode;
import todo.list.nganmtt.model.User;
import todo.list.nganmtt.repository.TaskRepository;
import todo.list.nganmtt.repository.UserRepository;

@Component("taskSecurity")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskSecurity {
    TaskRepository taskRepository;
    UserRepository userRepository;

    public boolean isOwner(String taskId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()
                -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (user == null) return false;

        return taskRepository.findById(taskId)
                .map(task -> task.getUser().getId().equals(user.getId()))
                .orElse(false);
    }


}
