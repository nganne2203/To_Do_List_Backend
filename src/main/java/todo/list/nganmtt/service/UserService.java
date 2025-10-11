package todo.list.nganmtt.service;

import todo.list.nganmtt.dto.request.UserUpdateRequest;
import todo.list.nganmtt.dto.response.UserResponse;

public interface UserService {
    UserResponse getMyInfo();

    UserResponse updateMyInfo(String id, UserUpdateRequest request);

    void deleteMyAccount(String id);

    void changePassword(String id, String newPassword, String oldPassword);

}
