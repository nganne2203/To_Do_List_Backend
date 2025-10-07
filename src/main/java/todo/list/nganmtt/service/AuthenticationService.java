package todo.list.nganmtt.service;

import todo.list.nganmtt.dto.request.AuthenticationRequest;
import todo.list.nganmtt.dto.request.UserCreationRequest;
import todo.list.nganmtt.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(UserCreationRequest request);

}
