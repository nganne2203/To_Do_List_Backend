package todo.list.nganmtt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import todo.list.nganmtt.dto.request.UserCreationRequest;
import todo.list.nganmtt.dto.request.UserUpdateRequest;
import todo.list.nganmtt.dto.response.UserResponse;
import todo.list.nganmtt.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    UserResponse toUserResponse(User user);

    @Mapping(ignore = true, target = "password")
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
