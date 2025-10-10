package todo.list.nganmtt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import todo.list.nganmtt.dto.request.ApiResult;
import todo.list.nganmtt.dto.request.TaskCreateRequest;
import todo.list.nganmtt.dto.request.TaskUpdateRequest;
import todo.list.nganmtt.dto.response.TaskResponse;
import todo.list.nganmtt.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TaskController {
    TaskService taskService;

    @GetMapping()
    public ApiResult<List<TaskResponse>> getAllTasksByUser() {
        return ApiResult.<List<TaskResponse>>builder()
                .result(taskService.getAllTasksByUser())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResult<TaskResponse> getTaskById(@PathVariable String id) {
        return ApiResult.<TaskResponse>builder()
                .result(taskService.getTaskById(id))
                .build();
    }

    @PostMapping()
    public ApiResult<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request) {
        return ApiResult.<TaskResponse>builder()
                .result(taskService.createTask(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResult<TaskResponse> updateTask(@PathVariable String id, @Valid @RequestBody TaskUpdateRequest request) {
        return ApiResult.<TaskResponse>builder()
                .result(taskService.updateTask(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResult<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ApiResult.<Void>builder()
                .build();
    }
}
