package todo.list.nganmtt.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.list.nganmtt.dto.request.ApiResult;
import todo.list.nganmtt.dto.request.AuthenticationRequest;
import todo.list.nganmtt.dto.request.UserCreationRequest;
import todo.list.nganmtt.dto.response.AuthenticationResponse;
import todo.list.nganmtt.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResult<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);

        return ApiResult.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/register")
    ApiResult<AuthenticationResponse> register(@RequestBody UserCreationRequest request) {
        var result = authenticationService.register(request);

        return ApiResult.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
}
