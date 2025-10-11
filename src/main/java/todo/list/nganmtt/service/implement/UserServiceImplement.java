package todo.list.nganmtt.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todo.list.nganmtt.dto.request.ChangePasswordRequest;
import todo.list.nganmtt.dto.request.UserUpdateRequest;
import todo.list.nganmtt.dto.response.UserResponse;
import todo.list.nganmtt.exception.AppException;
import todo.list.nganmtt.exception.ErrorCode;
import todo.list.nganmtt.mapper.UserMapper;
import todo.list.nganmtt.model.User;
import todo.list.nganmtt.repository.UserRepository;
import todo.list.nganmtt.service.UserService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImplement implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User byUserName = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(byUserName);
    }

    @Override
    public UserResponse updateMyInfo(String id, UserUpdateRequest request) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(updateUser, request);

        return userMapper.toUserResponse(userRepository.save(updateUser));
    }

    @Override
    public void deleteMyAccount(String id) {
        try {
            User deleteUser = userRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            userRepository.delete(deleteUser);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DELETE_USER_FAILED);
        }
    }

    @Override
    public void changePassword(String id, ChangePasswordRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getCurrentPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.INVALID_PASSWORD_OLD);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
