package todo.list.nganmtt.service;

import com.nimbusds.jose.JOSEException;
import todo.list.nganmtt.dto.request.*;
import todo.list.nganmtt.dto.response.AuthenticationResponse;
import todo.list.nganmtt.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(UserCreationRequest request);
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
