package todo.list.nganmtt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import todo.list.nganmtt.dto.request.ApiResult;
import todo.list.nganmtt.dto.request.UserUpdateRequest;
import todo.list.nganmtt.dto.response.UserResponse;
import todo.list.nganmtt.service.UserService;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @Operation(summary = "Get my user information", description = "Retrieve the authenticated user's information.", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user information", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping("/my-info")
    public ApiResult<UserResponse> getMyInfo() {
        return ApiResult.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @Operation(summary = "Update my user information", description = "Update the authenticated user's information.", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update of user information", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request because validation errors", content = @Content)
    })
    @PutMapping("/{id}")
    public ApiResult<UserResponse> updateMyInfo(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return ApiResult.<UserResponse>builder()
                .result(userService.updateMyInfo(id, request))
                .build();
    }

    @Operation(summary = "Delete my user account", description = "Delete the authenticated user's account.", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful deletion of user account", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server errors because delete user failed", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ApiResult<Void> deleteMyAccount(@PathVariable String id) {
        userService.deleteMyAccount(id);
        return ApiResult.<Void>builder().build();
    }
}
