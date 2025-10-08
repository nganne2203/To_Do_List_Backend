package todo.list.nganmtt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import todo.list.nganmtt.dto.request.ApiResult;
import todo.list.nganmtt.dto.request.AuthenticationRequest;
import todo.list.nganmtt.dto.request.UserCreationRequest;
import todo.list.nganmtt.dto.response.AuthenticationResponse;
import todo.list.nganmtt.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @Operation(summary = "User login", description = "Authenticate user and return a token", tags = {"Authentication"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ApiResult<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);

        return ApiResult.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @Operation(summary = "User registration", description = "Register a new user and return a token", tags = {"Authentication"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request because username or email is already exist", content = @Content)
    })
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ApiResult<AuthenticationResponse> register(@Valid @RequestBody UserCreationRequest request) {
        var result = authenticationService.register(request);

        return ApiResult.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
}
