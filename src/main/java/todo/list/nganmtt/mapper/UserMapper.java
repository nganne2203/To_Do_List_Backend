package todo.list.nganmtt.mapper;

import org.mapstruct.Mapper;
import todo.list.nganmtt.dto.request.UserCreationRequest;
import todo.list.nganmtt.dto.response.UserResponse;
import todo.list.nganmtt.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    UserResponse toUserResponse(User user);

}
