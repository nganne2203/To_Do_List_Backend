package todo.list.nganmtt.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import todo.list.nganmtt.dto.request.TaskCreateRequest;
import todo.list.nganmtt.dto.request.TaskUpdateRequest;
import todo.list.nganmtt.dto.response.TaskResponse;
import todo.list.nganmtt.exception.AppException;
import todo.list.nganmtt.exception.ErrorCode;
import todo.list.nganmtt.mapper.TaskMapper;
import todo.list.nganmtt.model.Task;
import todo.list.nganmtt.model.User;
import todo.list.nganmtt.repository.TaskRepository;
import todo.list.nganmtt.repository.UserRepository;
import todo.list.nganmtt.service.TaskService;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImplement implements TaskService {
    UserRepository userRepository;
    TaskMapper taskMapper;
    TaskRepository taskRepository;

    @Override
    public List<TaskResponse> getAllTasksByUser() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User byUserName = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return taskRepository.findAllByUserId(byUserName.getId())
                .stream()
                .map(taskMapper::toTaskResponse)
                .toList();
    }

    @Override
    @PreAuthorize("@taskSecurity.isOwner(#id)")
    public TaskResponse getTaskById(String id) {
        return taskMapper.toTaskResponse(taskRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_FOUND)));
    }

    @Override
    public TaskResponse createTask(TaskCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Task task = taskMapper.toTask(request);
        task.setUser(user);

        return taskMapper.toTaskResponse(taskRepository.save(task));
    }

    @Override
    @PreAuthorize("@taskSecurity.isOwner(#id)")
    public TaskResponse updateTask(String id, TaskUpdateRequest request) {
        Task updateTask = taskRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_FOUND));
        taskMapper.updateTask(updateTask, request);
        return taskMapper.toTaskResponse(taskRepository.save(updateTask));
    }

    @Override
    @PreAuthorize("@taskSecurity.isOwner(#id)")
    public void deleteTask(String id) {
        taskRepository.deleteTaskById(id);
    }

}
