package todo.list.nganmtt.configuration;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;
import todo.list.nganmtt.dto.request.IntrospectRequest;
import todo.list.nganmtt.dto.response.IntrospectResponse;
import todo.list.nganmtt.service.AuthenticationService;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.secret}")
    private String signerKey;

    @Autowired
    @Lazy
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder jwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        IntrospectResponse response = null;
        try {
            response = authenticationService.introspect(IntrospectRequest.builder()
                            .token(token)
                    .build());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        if (!response.isValid())
            throw new JwtException("Invalid token");

        if (Objects.isNull(jwtDecoder)) {
            SecretKeySpec key = new SecretKeySpec(signerKey.getBytes(), "HmacSHA256");
            jwtDecoder = NimbusJwtDecoder
                    .withSecretKey(key)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return jwtDecoder.decode(token);
    }
}
