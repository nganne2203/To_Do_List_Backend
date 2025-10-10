package todo.list.nganmtt.controller;

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

    @PostMapping("/my-info")
    public ApiResult<UserResponse> getMyInfo() {
        return ApiResult.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResult<UserResponse> updateMyInfo(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return ApiResult.<UserResponse>builder()
                .result(userService.updateMyInfo(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResult<Void> deleteMyAccount(@PathVariable String id) {
        userService.deleteMyAccount(id);
        return ApiResult.<Void>builder().build();
    }
}
