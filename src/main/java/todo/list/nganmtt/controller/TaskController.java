package todo.list.nganmtt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all tasks for the authenticated user", description = "Retrieve a list of all tasks associated with the authenticated user.", tags = {"Tasks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of tasks", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation =  TaskResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping()
    public ApiResult<List<TaskResponse>> getAllTasksByUser() {
        return ApiResult.<List<TaskResponse>>builder()
                .result(taskService.getAllTasksByUser())
                .build();
    }

    @Operation(summary = "Get a task by ID", description = "Retrieve a specific task by its ID for the authenticated user.", tags = {"Tasks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the task", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ApiResult<TaskResponse> getTaskById(@PathVariable String id) {
        return ApiResult.<TaskResponse>builder()
                .result(taskService.getTaskById(id))
                .build();
    }


    @Operation(summary = "Create a new task", description = "Create a new task for the authenticated user.", tags = {"Tasks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful creation of the task", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request due to validation errors", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping()
    public ApiResult<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request) {
        return ApiResult.<TaskResponse>builder()
                .result(taskService.createTask(request))
                .build();
    }

    @Operation(summary = "Update an existing task", description = "Update an existing task by its ID for the authenticated user.", tags = {"Tasks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of the task", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request due to validation errors", content = @Content),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ApiResult<TaskResponse> updateTask(@PathVariable String id, @Valid @RequestBody TaskUpdateRequest request) {
        return ApiResult.<TaskResponse>builder()
                .result(taskService.updateTask(id, request))
                .build();
    }

    @Operation(summary = "Delete a task", description = "Delete a specific task by its ID for the authenticated user.", tags = {"Tasks"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful deletion of the task", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ApiResult<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ApiResult.<Void>builder()
                .build();
    }
}
