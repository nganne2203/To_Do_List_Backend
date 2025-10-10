package todo.list.nganmtt.service;

import todo.list.nganmtt.dto.request.TaskCreateRequest;
import todo.list.nganmtt.dto.request.TaskUpdateRequest;
import todo.list.nganmtt.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTasksByUser();

    TaskResponse getTaskById(String id);

    TaskResponse createTask(TaskCreateRequest request);

    TaskResponse updateTask(String id, TaskUpdateRequest request);

    void deleteTask(String id);

}
