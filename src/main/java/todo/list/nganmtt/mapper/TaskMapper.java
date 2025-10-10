package todo.list.nganmtt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import todo.list.nganmtt.dto.request.TaskCreateRequest;
import todo.list.nganmtt.dto.request.TaskUpdateRequest;
import todo.list.nganmtt.dto.response.TaskResponse;
import todo.list.nganmtt.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskCreateRequest request);

    TaskResponse toTaskResponse(Task task);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    void updateTask(@MappingTarget Task task, TaskUpdateRequest taskUpdateRequest);

}
